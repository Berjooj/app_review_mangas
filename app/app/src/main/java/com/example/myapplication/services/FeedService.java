package com.example.myapplication.services;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.interfaces.ObraServiceDone;
import com.example.myapplication.interfaces.ServiceDone;
import com.example.myapplication.models.FavoritoToken;
import com.example.myapplication.models.Obra;
import com.example.myapplication.models.ObraResponse;
import com.example.myapplication.models.ObraToken;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.repositories.RepositorioUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FeedService {

    public static void carregaFeedCompleto(ServiceDone callback) {
        ApplicationService applicationService = ApplicationService.getInstance();

        try {
            RepositorioObras repositorioObras = RepositorioObras.getInstance();
            repositorioObras.limparListas();

            FeedService.getFeed(0, "em_alta", null, onSuccessEmAlta -> {
                try {
                    FeedService.getFeed(0, "em_breve", null, onSuccessEmBreve -> {
                        try {
                            FeedService.getFeed(0, "lancamentos", null, onSuccessLancamento -> {
                                if (callback != null) {
                                    callback.onServiceDone(new ObraResponse("Feed carregado", 200, null));
                                }
                            }, onErrorLancamento -> {
                                Toast.makeText(applicationService.getContext(), onErrorLancamento.mensagem, Toast.LENGTH_SHORT).show();
                            });
                        } catch (JSONException e) {
                            Toast.makeText(applicationService.getContext(), "Erro ao carregar a lista 'lançamentos'", Toast.LENGTH_SHORT).show();
                        }
                    }, onErrorEmBreve -> {
                        Toast.makeText(applicationService.getContext(), onErrorEmBreve.mensagem, Toast.LENGTH_SHORT).show();
                    });
                } catch (JSONException e) {
                    Toast.makeText(applicationService.getContext(), "Erro ao carregar a lista 'lançamentos'", Toast.LENGTH_SHORT).show();
                }
            }, onErrorEmAlta -> {
                Toast.makeText(applicationService.getContext(), onErrorEmAlta.mensagem, Toast.LENGTH_SHORT).show();
            });
        } catch (JSONException e) {
            Toast.makeText(applicationService.getContext(), "Erro ao carregar a lista 'em alta'", Toast.LENGTH_SHORT).show();
        }
    }

    public static void carregarPesquisa(String filtro, ServiceDone callback) {
        ApplicationService applicationService = ApplicationService.getInstance();

        try {
            RepositorioObras repositorioObras = RepositorioObras.getInstance();
            repositorioObras.listaFiltro = new ArrayList<>();

            FeedService.getFeed(0, null, filtro, onSuccessEmAlta -> {
                if (callback != null) {
                    callback.onServiceDone(new ObraResponse("Feed carregado", 200, null));
                }
            }, onErrorEmAlta -> {
                if (callback != null) {
                    callback.onServiceDone(new ObraResponse("Erro ao carregar a busca", 500, null));
                }

                Toast.makeText(applicationService.getContext(), "Erro ao carregar a busca", Toast.LENGTH_SHORT).show();
            });
        } catch (JSONException e) {
            Toast.makeText(applicationService.getContext(), "Erro ao carregar a busca", Toast.LENGTH_SHORT).show();
        }
    }

    public static void getFeed(int pagina, String tipoFiltro, String filtro, ObraServiceDone onSuccess, ObraServiceDone onError) throws JSONException {
        ApplicationService appService = ApplicationService.getInstance();

        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        Usuario usuario = repositorioUsuario.getUsuario();

        RepositorioFavoritos repositorioFavoritos = RepositorioFavoritos.getInstance();

        RepositorioObras repositorioObras = RepositorioObras.getInstance();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", pagina);
        jsonObject.put("tipoFiltro", tipoFiltro);
        jsonObject.put("filtro", filtro);

        String url = "https://api.berjooj.cloud/api/feed";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, response -> {
            try {
                final int status = response.getInt("status");
                String message = response.getString("message");

                if (status != 200) {
                    throw new Exception(message);
                }

                JSONArray data = response.getJSONArray("data");

                for (int i = 0; i < data.length(); i++) {
                    ObraToken obraToken = appService.gson.fromJson(data.get(i).toString(), ObraToken.class);

                    Obra obra = new Obra();

                    obra.id = obraToken.getId();
                    obra.idTipo = obraToken.getId_tipo();
                    obra.titulo = obraToken.getTitulo();
                    obra.subtitulo = obraToken.getSubtitulo();
                    obra.qtEpisodios = obraToken.getQt_episodios();
                    obra.qtVolumes = obraToken.getQt_volumes();
                    obra.qtFavoritos = obraToken.getQt_favoritos();
                    obra.nota = obraToken.getNota();
                    obra.qtAvaliacoes = obraToken.getQt_avaliacoes();
                    obra.urlImagem = obraToken.getUrl_imagem();
                    obra.dataLancamento = obraToken.getData_lancamento();
                    obra.criacao = obraToken.getCreated_at();
                    obra.descricao = obraToken.getDescricao();

                    for (int j = 0; j < obraToken.getCategorias().size(); j++) {
                        obra.categorias.add(obraToken.getCategorias().get(j).getNome());
                    }
                    obra.favoritada = repositorioFavoritos.obraLista.stream().anyMatch(_obra -> _obra.id == obra.id);

                    if (tipoFiltro == null) {
                        repositorioObras.listaFiltro.add(obra);
                    } else {
                        switch (tipoFiltro) {
                            case "lancamentos":
                                repositorioObras.lancamentoLista.add(obra);
                                break;
                            case "em_breve":
                                repositorioObras.emBreveLista.add(obra);
                                break;
                            case "em_alta":
                                repositorioObras.emAltaLista.add(obra);
                                break;
                        }
                    }
                }

                if (onSuccess != null) {
                    onSuccess.onServiceDone(new ObraResponse(message, status, null));
                }

            } catch (Exception e) {
                Log.wtf("Acá", e.toString());
                if (onError != null) {
                    onError.onServiceDone(new ObraResponse(e.getMessage(), 500, null));
                }
            }
        }, error -> {
            if (onError != null) {
                onError.onServiceDone(new ObraResponse(error.getMessage(), 500, null));
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

}
