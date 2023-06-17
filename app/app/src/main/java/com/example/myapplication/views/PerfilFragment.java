package com.example.myapplication.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.models.UsuarioResponse;
import com.example.myapplication.repositories.RepositorioUsuario;
import com.example.myapplication.services.UsuarioService;

public class PerfilFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.perfil_fragment, container, false);

        TextView nomeUsuario = view.findViewById(R.id.textViewPerfilNome);
        TextView emailUsuario = view.findViewById(R.id.textViewPerfilEmail);

        nomeUsuario.setText(RepositorioUsuario.getInstance().getUsuario().nome);
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
            RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
//            repositorioUsuario.getUsuario().password =
//            UsuarioService.atualizarUsuario(usu);
        });

        return view;
    }
}