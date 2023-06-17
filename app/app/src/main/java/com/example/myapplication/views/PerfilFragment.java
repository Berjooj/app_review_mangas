package com.example.myapplication.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.models.UsuarioResponse;
import com.example.myapplication.repositories.RepositorioUsuario;
import com.example.myapplication.services.UsuarioService;

import org.json.JSONException;

public class PerfilFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.perfil_fragment, container, false);

        TextView nomeUsuario = view.findViewById(R.id.textViewPerfilNome);
        TextView emailUsuario = view.findViewById(R.id.textViewPerfilEmail);
        EditText perfilNovaSenha = view.findViewById(R.id.perfilNovaSenhaId);
        EditText perfilConfNovaSenha = view.findViewById(R.id.perfilConfNovaSenhaId);

        nomeUsuario.setText(RepositorioUsuario.getInstance().getUsuario().nome);
        Log.wtf("TAG", RepositorioUsuario.getInstance().getUsuario().nome );
        emailUsuario.setText(RepositorioUsuario.getInstance().getUsuario().email);

        ImageButton voltarActivity = view.findViewById(R.id.sairId);
        voltarActivity.setOnClickListener(view1 -> {
            RepositorioUsuario.getInstance().logout();

            Intent intent = new Intent(requireActivity(), LandingPage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        Button salvar = view.findViewById(R.id.btnSalvarUsuario);
        salvar.setOnClickListener(v -> {
            String senha1 = perfilNovaSenha.getText().toString();
            String senha2 = perfilConfNovaSenha.getText().toString();
            Log.wtf("TAG", senha1 );
            Log.wtf("TAG", senha2 );
            if (senha1.equals(senha2)) {
                {
                    RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
                    Usuario usuario = repositorioUsuario.getUsuario();
                    usuario.password = senha1;
                    Log.wtf("TAG", senha1);
                    try {
                        UsuarioService.atualizarUsuario(usuario, onServiceDone -> {
                                    Log.wtf("Usuario", "Atualizado");
                            Toast.makeText(getContext(), "Senha alterada", Toast.LENGTH_LONG).show();
                                }, onError -> {
                                    Log.wtf("Usuario", onError.mensagem);
                                });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else{
                Toast.makeText(getContext(), "Senhas Diferentes", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}