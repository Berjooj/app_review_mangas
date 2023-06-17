package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.InitContext;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.services.ApplicationService;
import com.example.myapplication.services.UsuarioService;

public class Login extends AppCompatActivity implements InitContext {

    private EditText newEmailEditText;
    private EditText newSenhaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        this.setInstance();

        newEmailEditText = findViewById(R.id.emailId);
        newSenhaEditText = findViewById(R.id.SenhaId);

        ImageButton voltarActivity = findViewById(R.id.voltarId);
        voltarActivity.setOnClickListener(view -> {
            finish();
        });

        Button botaoEnviar = findViewById(R.id.enviarId);
        botaoEnviar.setOnClickListener(view -> {
            String newEmail = newEmailEditText.getText().toString();
            String newSenha = newSenhaEditText.getText().toString();

            try {
                ApplicationService service = ApplicationService.getInstance();
                service.loader.showDialog();

                UsuarioService.loginRequest(
                        new Usuario(newEmail, newSenha),
                        success -> {
                            service.loader.dismiss();

                            Intent intentSplashPage = new Intent(getApplicationContext(), SplashPage.class);
                            intentSplashPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intentSplashPage);

                            finish();
                        }, error -> {
                            service.loader.dismiss();
                            Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_LONG).show();
                        });
            } catch (Exception e) {
                Toast.makeText(this, "Puts ai deu ruim :/", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void setInstance() {
        ApplicationService service = ApplicationService.getInstance();
        service.loader = new LoadingDialog(Login.this);
        service.setContext(this);
    }
}