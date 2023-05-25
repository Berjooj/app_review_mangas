package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    private EditText newEmailEditText;
    private EditText newSenhaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        newEmailEditText = findViewById(R.id.emailLoginId);
        newSenhaEditText = findViewById(R.id.emailSenhaId);

        ImageButton voltarActivity = findViewById(R.id.voltarId);
        voltarActivity.setOnClickListener(view -> {
            finish();
        });
        Button botaoEnviar = findViewById(R.id.enviarId);
        botaoEnviar.setOnClickListener(view -> {
            String newEmail = newEmailEditText.getText().toString();
            String newSenha = newSenhaEditText.getText().toString();

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("email", newEmail);
                jsonObject.put("password", newSenha);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String url = "https://api.berjooj.cloud/api/auth/login";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, jsonObject,
                    response -> {
                        try {
                            final int status = response.getInt("status");
                            String message = response.getString("message");
                            Toast.makeText(MainActivity3.this, status + ": " + message, Toast.LENGTH_SHORT).show();
                            if(status==200){
                                Intent intentLogado = new Intent(getApplicationContext(), MainActivity4.class);
                                startActivity(intentLogado);
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> {
                        int statusCode = error.networkResponse.statusCode;
                        Toast.makeText(MainActivity3.this, "Erro: " + statusCode, Toast.LENGTH_SHORT).show();
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity3.this);
            requestQueue.add(request);
        });
    }
}