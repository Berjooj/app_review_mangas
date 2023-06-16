package com.example.myapplication.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CardAdapter;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioFavoritos;

import java.util.List;


public class FavoritosFragment extends Fragment {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RepositorioFavoritos repositorioFavoritos = RepositorioFavoritos.getInstance();
        View view = inflater.inflate(R.layout.favoritos_fragment, container, false);
        TextView nomeUsuario = view.findViewById(R.id.nomeUsuarioId);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerGridId);
        List<Obra> obras = repositorioFavoritos.obraLista;

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(new CardAdapter(getActivity(), obras));


        return view;
    }
}