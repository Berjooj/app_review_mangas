package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.myapplication.repositories.RepositorioUsuario;
import com.example.myapplication.services.ServiceDone;
import com.example.myapplication.services.ServicosUsuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText newEmailEditText;
    private EditText newSenhaEditText;
    RepositorioUsuario repoUsuario;
    SharedPreferences preferencias;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.repoUsuario = RepositorioUsuario.getInstance();
        //Cache de dados de Login
        preferencias = getSharedPreferences("MyPreferencias",MODE_PRIVATE);
        editor = preferencias.edit();

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
                editor.putInt("id",repoUsuario.getUsuario().id);
                editor.putString("nome",repoUsuario.getUsuario().nome);
                editor.putString("email",repoUsuario.getUsuario().email);
                editor.putString("senha",newSenha);
                editor.putInt("idFotoPerfil",repoUsuario.getUsuario().idFotoPerfil);
                editor.putString("token",repoUsuario.getUsuario().token);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            });
        });
    }
}