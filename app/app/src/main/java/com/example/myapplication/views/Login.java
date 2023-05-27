package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.services.ServiceDone;
import com.example.myapplication.services.ServicosUsuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText newEmailEditText;
    private EditText newSenhaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

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
            ServicosUsuario.getLogin(getApplicationContext(), newEmail, newSenha, () -> {
                Log.wtf("Login", "Batata Frita");
                finish();
            });
        });
    }
}