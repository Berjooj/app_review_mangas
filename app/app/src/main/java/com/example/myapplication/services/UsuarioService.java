package com.example.myapplication.services;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.interfaces.UsuarioServiceDone;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.models.UsuarioResponse;
import com.example.myapplication.repositories.RepositorioUsuario;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UsuarioService {

    /**
     * @param usuario   Usuario
     * @param onSuccess CadastroUsuario callback
     * @param onError   ServiceError callback
     * @throws Exception erro
     */
    public static void loginRequest(Usuario usuario, UsuarioServiceDone onSuccess, UsuarioServiceDone onError) throws Exception {
        ApplicationService appService = ApplicationService.getInstance();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", usuario.email);
        jsonObject.put("password", usuario.password);

        String url = "https://api.berjooj.cloud/api/auth/login";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                response -> {
                    try {
                        final int status = response.getInt("status");
                        String message = response.getString("message");

                        if (status != 200) {
                            throw new Exception(message);
                        }

                        JSONObject data = response.getJSONObject("data");
                        Usuario usuarioLogado = appService.gson.fromJson(data.getString("user"), new TypeToken<Usuario>() {
                        }.getType());

                        usuarioLogado.password = usuario.password;
                        usuarioLogado.token = response.getString("data");

                        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
                        repositorioUsuario.setUsuario(usuarioLogado);

                        if (onSuccess != null) {
                            onSuccess.onServiceDone(new UsuarioResponse(message, status, usuarioLogado));
                        }

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
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(appService.getContext());
        requestQueue.add(request);
    }

    /**
     * @param usuario   Usuario
     * @param onSuccess CadastroUsuario callback
     * @param onError   ServiceError callback
     * @throws Exception erro
     */
    public static void cadastroUsuario(Usuario usuario, UsuarioServiceDone onSuccess, UsuarioServiceDone onError) throws Exception {
        ApplicationService appService = ApplicationService.getInstance();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nome", usuario.nome);
        jsonObject.put("email", usuario.email);
        jsonObject.put("password", usuario.password);

        String url = "https://api.berjooj.cloud/api/auth/register";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                response -> {
                    try {
                        int status = response.getInt("status");
                        String message = response.getString("message");

                        if (status != 200) {
                            throw new Exception(message);
                        }

                        Usuario novoUsuario = appService.gson.fromJson(response.getString("data"), new TypeToken<Usuario>() {
                        }.getType());

                        novoUsuario.password = usuario.password;

                        if (onSuccess != null) {
                            onSuccess.onServiceDone(new UsuarioResponse(message, status, novoUsuario));
                        }

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
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(appService.getContext());
        requestQueue.add(request);
    }
}
