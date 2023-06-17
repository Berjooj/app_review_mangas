package com.example.myapplication.services;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.interfaces.ServiceDone;
import com.example.myapplication.models.Avaliacao;
import com.example.myapplication.models.AvaliacaoToken;
import com.example.myapplication.models.ObraResponse;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.models.UsuarioResponse;
import com.example.myapplication.repositories.RepositorioAvalicao;
import com.example.myapplication.repositories.RepositorioUsuario;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AvaliacaoService {

    public static void buscarComentarios(int idObra, ServiceDone onSuccess, ServiceDone onError) {
        ApplicationService appService = ApplicationService.getInstance();

        RepositorioAvalicao repositorioAvalicao = RepositorioAvalicao.getInstance();
        repositorioAvalicao.clear();

        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        Usuario usuario = repositorioUsuario.getUsuario();

        JSONObject jsonObject = new JSONObject();

        String url = "https://api.berjooj.cloud/api/avaliar/" + String.valueOf(idObra);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, jsonObject, response -> {
            try {
                final int status = response.getInt("status");
                String message = response.getString("message");

                if (status != 200) {
                    throw new Exception(message);
                }

                JSONArray data = response.getJSONArray("data");

                for (int i = 0; i < data.length(); i++) {
                    AvaliacaoToken token = appService.gson.fromJson(data.get(i).toString(), AvaliacaoToken.class);

                    Avaliacao avaliacao = new Avaliacao();
                    avaliacao.id = token.getId();
                    avaliacao.idUsuario = token.getId_usuario();
                    avaliacao.idObra = token.getId_obra();
                    avaliacao.nota = token.getNota();
                    avaliacao.comentario = token.getComentario();

                    avaliacao.qtCurtidas = token.getComentario().length();
                    avaliacao.created_at = token.getCreated_at();
                    avaliacao.nome = token.getNome();

                    if (usuario.id == avaliacao.idUsuario) {
                        repositorioAvalicao.comentarioUsuarioLogado = avaliacao;
                    }

                    avaliacao.curtiu = token.getCurtidas().stream().anyMatch(curtidaToken -> curtidaToken.getId_usuario() == usuario.id);

                    repositorioAvalicao.avaliacaoLista.add(avaliacao);
                }

                if (onSuccess != null) {
                    onSuccess.onServiceDone(new ObraResponse(message, status, null));
                }

            } catch (Exception e) {
                if (onError != null) {
                    onError.onServiceDone(new UsuarioResponse(e.getMessage(), 500, null));
                }
            }
        }, error -> {
            if (onError != null) {
                onError.onServiceDone(new UsuarioResponse(error.getMessage(), 500, null));
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + usuario.token);
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(appService.getContext());
        requestQueue.add(request);
    }

    public static void criarComentario(ServiceDone onSuccess, ServiceDone onError) throws JSONException {
        ApplicationService appService = ApplicationService.getInstance();

        RepositorioAvalicao repositorioAvalicao = RepositorioAvalicao.getInstance();
        Avaliacao novaAvaliacao = repositorioAvalicao.comentarioUsuarioLogado;

        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        Usuario usuario = repositorioUsuario.getUsuario();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id_obra", novaAvaliacao.idObra);
        jsonObject.put("nota", novaAvaliacao.nota);
        jsonObject.put("comentario", novaAvaliacao.comentario);

        String url = "https://api.berjooj.cloud/api/avaliar";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, response -> {
            try {
                final int status = response.getInt("status");
                String message = response.getString("message");

                if (status != 200) {
                    throw new Exception(message);
                }

                AvaliacaoService.buscarComentarios(novaAvaliacao.idObra, onSuccessUpdate -> {
                    if (onSuccess != null) {
                        onSuccess.onServiceDone(new ObraResponse(message, status, null));
                    }
                }, onErrorUpdate -> {
                    if (onError != null) {
                        onError.onServiceDone(new ObraResponse(onErrorUpdate.mensagem, 500, null));
                    }
                });

            } catch (Exception e) {
                if (onError != null) {
                    onError.onServiceDone(new UsuarioResponse(e.getMessage(), 500, null));
                }
            }
        }, error -> {
            if (onError != null) {
                onError.onServiceDone(new UsuarioResponse(error.getMessage(), 500, null));
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + usuario.token);
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(appService.getContext());
        requestQueue.add(request);
    }

    public static void atualizaCurtidaAvaliacao(Avaliacao atualizaAvaliacao, ServiceDone onSuccess, ServiceDone onError) throws JSONException {
        ApplicationService appService = ApplicationService.getInstance();

        RepositorioAvalicao repositorioAvalicao = RepositorioAvalicao.getInstance();
        Avaliacao novaAvaliacao = repositorioAvalicao.comentarioUsuarioLogado;

        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        Usuario usuario = repositorioUsuario.getUsuario();

        JSONObject jsonObject = new JSONObject();

        if (atualizaAvaliacao.curtiu) {
            jsonObject.put("id_obra", novaAvaliacao.idObra);
            jsonObject.put("nota", novaAvaliacao.nota);
            jsonObject.put("comentario", novaAvaliacao.comentario);
        }

        String url = atualizaAvaliacao.curtiu
                ? "https://api.berjooj.cloud/api/curtir"
                : "https://api.berjooj.cloud/api/curtir" + String.valueOf(atualizaAvaliacao.id);

        JsonObjectRequest request = new JsonObjectRequest(
                atualizaAvaliacao.curtiu
                        ? Request.Method.POST
                        : Request.Method.DELETE,
                url,
                jsonObject,
                response -> {
                    try {
                        final int status = response.getInt("status");
                        String message = response.getString("message");

                        if (status != 200) {
                            throw new Exception(message);
                        }

                        AvaliacaoService.buscarComentarios(novaAvaliacao.idObra, onSuccessUpdate -> {
                            if (onSuccess != null) {
                                onSuccess.onServiceDone(new ObraResponse(message, status, null));
                            }
                        }, onErrorUpdate -> {
                            if (onError != null) {
                                onError.onServiceDone(new ObraResponse(onErrorUpdate.mensagem, 500, null));
                            }
                        });

                    } catch (Exception e) {
                        if (onError != null) {
                            onError.onServiceDone(new UsuarioResponse(e.getMessage(), 500, null));
                        }
                    }
                },
                error -> {
                    if (onError != null) {
                        onError.onServiceDone(new UsuarioResponse(error.getMessage(), 500, null));
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + usuario.token);
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(appService.getContext());
        requestQueue.add(request);
    }
}
