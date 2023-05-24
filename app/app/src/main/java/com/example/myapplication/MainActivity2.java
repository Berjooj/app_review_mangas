package com.example.myapplication;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private EditText newNameEditText;
    private EditText newEmailEditText;
    private EditText newSenhaEditText;
    private EditText newSenhaCEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        newNameEditText = findViewById(R.id.newNameId);
        newEmailEditText = findViewById(R.id.newEmailId);
        newSenhaEditText = findViewById(R.id.newSenhaId);
        newSenhaCEditText = findViewById(R.id.newSenhaCId);

        ImageButton voltarActivity = findViewById(R.id.voltarId);
        voltarActivity.setOnClickListener(view -> {
            Intent intentVoltar = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentVoltar);
        });

        Button botaoEnviar = findViewById(R.id.enviarId);
        botaoEnviar.setOnClickListener(view -> {
            String newName = newNameEditText.getText().toString();
            String newEmail = newEmailEditText.getText().toString();
            String newSenha = newSenhaEditText.getText().toString();

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", newName);
                //jsonObject.put("email", newEmail);
                jsonObject.put("password", newSenha);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String url = "https://api.berjooj.cloud/api/auth/register";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    response -> {
                        Toast.makeText(MainActivity2.this, "Solicitação bem-sucedida", Toast.LENGTH_SHORT).show();
                    },
                    error -> {
                        int statusCode = error.networkResponse.statusCode;
                        Toast.makeText(MainActivity2.this, "Erro: " + statusCode, Toast.LENGTH_SHORT).show();
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
            requestQueue.add(request);
        });
    }

}