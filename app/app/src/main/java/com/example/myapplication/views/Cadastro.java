package com.example.myapplication.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity {

    private EditText newNameEditText;
    private EditText newEmailEditText;
    private EditText newSenhaEditText;
    private EditText newSenhaCEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        newNameEditText = findViewById(R.id.NameCadastroId);
        newEmailEditText = findViewById(R.id.EmailCadastroId);
        newSenhaEditText = findViewById(R.id.SenhaCadastroId);
        newSenhaCEditText = findViewById(R.id.confSenhaCadastroId);

        ImageButton voltarActivity = findViewById(R.id.voltarId);
        voltarActivity.setOnClickListener(view -> {
            finish();
        });

        Button botaoEnviar = findViewById(R.id.enviarId);
        botaoEnviar.setOnClickListener(view -> {
            String newName = newNameEditText.getText().toString();
            String newEmail = newEmailEditText.getText().toString();
            String newSenha = newSenhaEditText.getText().toString();

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nome", newName);
                jsonObject.put("email", newEmail);
                jsonObject.put("password", newSenha);

                String url = "https://api.berjooj.cloud/api/auth/register";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                        response -> {
                            try {
                                final int status = response.getInt("status");
                                String message = response.getString("message");
                                Toast.makeText(Cadastro.this, status + ": " + message, Toast.LENGTH_SHORT).show();
                                if (status == 200) {
                                    finish();
                                }

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        error -> {
                            int statusCode = error.networkResponse.statusCode;
                            Toast.makeText(Cadastro.this, "Erro: " + statusCode, Toast.LENGTH_SHORT).show();
                        }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(Cadastro.this);
                requestQueue.add(request);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

}