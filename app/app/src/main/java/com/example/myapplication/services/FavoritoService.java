package com.example.myapplication.services;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.interfaces.ObraServiceDone;
import com.example.myapplication.models.FavoritoToken;
import com.example.myapplication.models.Obra;
import com.example.myapplication.models.ObraResponse;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FavoritoService {

    public static void getFavoritos(ObraServiceDone onSuccess, ObraServiceDone onError) {
        ApplicationService appService = ApplicationService.getInstance();

        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        Usuario usuario = repositorioUsuario.getUsuario();

        RepositorioFavoritos repositorioFavoritos = RepositorioFavoritos.getInstance();
        repositorioFavoritos.clear();

        JSONObject jsonObject = new JSONObject();

        String url = "https://api.berjooj.cloud/api/favoritos";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, jsonObject, response -> {
            try {
                final int status = response.getInt("status");
                String message = response.getString("message");

                if (status != 200) {
                    throw new Exception(message);
                }

                JSONArray data = response.getJSONArray("data");

                for (int i = 0; i < data.length(); i++) {
                    FavoritoToken favorito = appService.gson.fromJson(data.get(i).toString(), FavoritoToken.class);

                    Obra obra = new Obra();

                    obra.id = favorito.getObra().getId();
                    obra.idFavorito = favorito.getId();
                    obra.idTipo = favorito.getObra().getTipoId();
                    obra.titulo = favorito.getObra().getTitulo();
                    obra.subtitulo = favorito.getObra().getSubtitulo();
                    obra.qtEpisodios = favorito.getObra().getQuantidadeEpisodios();
                    obra.qtVolumes = favorito.getObra().getQuantidadeVolumes();
                    obra.qtFavoritos = favorito.getObra().getQuantidadeFavoritos();
                    obra.nota = favorito.getObra().getNota();
                    obra.qtAvaliacoes = favorito.getObra().getQuantidadeAvaliacoes();
                    obra.urlImagem = favorito.getObra().getUrlImagem();
                    obra.dataLancamento = favorito.getObra().getDataLancamento();
                    obra.criacao = favorito.getObra().getCreatedAt();

                    repositorioFavoritos.obraLista.add(obra);
                }

                repositorioFavoritos.sync();

                if (onSuccess != null) {
                    onSuccess.onServiceDone(new ObraResponse(message, status, null));
                }

            } catch (Exception e) {
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

    public static void addFavoritos(int idObra, ObraServiceDone onSuccess, ObraServiceDone onError) throws JSONException {
        ApplicationService appService = ApplicationService.getInstance();

        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        Usuario usuario = repositorioUsuario.getUsuario();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id_obra", idObra);

        String url = "https://api.berjooj.cloud/api/favoritos";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, response -> {
            try {
                final int status = response.getInt("status");
                String message = response.getString("message");

                if (status != 200) {
                    throw new Exception(message);
                }

                FavoritoService.getFavoritos(onSuccess, onError);

            } catch (Exception e) {
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

    public static void removerFavoritos(int idFavorito, ObraServiceDone onSuccess, ObraServiceDone onError) {
        ApplicationService appService = ApplicationService.getInstance();

        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        Usuario usuario = repositorioUsuario.getUsuario();

        RepositorioFavoritos repositorioFavoritos = RepositorioFavoritos.getInstance();

        JSONObject jsonObject = new JSONObject();

        String url = "https://api.berjooj.cloud/api/favoritos/" + String.valueOf(idFavorito);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, jsonObject, response -> {
            try {
                final int status = response.getInt("status");
                String message = response.getString("message");

                if (status != 200) {
                    throw new Exception(message);
                }

                repositorioFavoritos.removeObraFavoritos(idFavorito);

                if (onSuccess != null) {
                    onSuccess.onServiceDone(new ObraResponse(message, status, null));
                }

            } catch (Exception e) {
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
