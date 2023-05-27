package com.example.myapplication.services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.views.HomePage;
import com.example.myapplication.views.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ServicosUsuario {



    public static void getLogin(Context contexto, String email, String senha,ServiceDone callback){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", senha);

            String url = "https://api.berjooj.cloud/api/auth/login";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    response -> {
                        try {
                            final int status = response.getInt("status");
                            String message = response.getString("message");
                            Toast.makeText(contexto, status + ": " + message, Toast.LENGTH_SHORT).show();
                            if (status == 200) {
                                if (callback != null) {
                                    callback.onServiceDone();
                                }
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> {
                        int statusCode = error.networkResponse.statusCode;
                        Toast.makeText(contexto, "Erro: " + statusCode, Toast.LENGTH_SHORT).show();
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(contexto);
            requestQueue.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public static void cadastrarUsuario(Usuario usuario){

    }

}
