package com.example.myapplication.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.InitContext;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.services.ApplicationService;
import com.example.myapplication.services.UsuarioService;

public class Cadastro extends AppCompatActivity implements InitContext {

    private EditText newNameEditText;
    private EditText newEmailEditText;
    private EditText newSenhaEditText;
    private EditText newSenhaCEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        this.setInstance();

        newNameEditText = findViewById(R.id.newNameId);
        newEmailEditText = findViewById(R.id.newEmailId);

        // TODO: comparar as 2 senhas
        newSenhaEditText = findViewById(R.id.newSenhaId);
        newSenhaCEditText = findViewById(R.id.newSenhaCId);

        ImageButton voltarActivity = findViewById(R.id.voltarId);
        voltarActivity.setOnClickListener(view -> {
            finish();
        });

        Button botaoEnviar = findViewById(R.id.enviarId);
        botaoEnviar.setOnClickListener(view -> {
            String newName = newNameEditText.getText().toString();
            String newEmail = newEmailEditText.getText().toString();
            String newSenha = newSenhaEditText.getText().toString();

            try {
                UsuarioService.cadastroUsuario(
                        new Usuario(newName, newEmail, newSenha),
                        onSuccess -> {
                            try {
                                UsuarioService.loginRequest(
                                        onSuccess.data,
                                        success -> {
                                            Intent intentSplashPage = new Intent(getApplicationContext(), SplashPage.class);
                                            intentSplashPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intentSplashPage);

                                            finish();
                                        }, error -> {
                                            Toast.makeText(this, "Erro ao autenticar o usuário", Toast.LENGTH_LONG).show();
                                        });
                            } catch (Exception e) {
                                Toast.makeText(this, "Puts ai deu ruim :/", Toast.LENGTH_LONG).show();
                            }
                        },
                        onError -> {
                            Toast.makeText(this, "Erro ao cadastrar o usuário", Toast.LENGTH_LONG).show();
                        }
                );
            } catch (Exception exception) {
                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void setInstance() {
        ApplicationService service = ApplicationService.getInstance();
        service.setContext(this);
    }
}