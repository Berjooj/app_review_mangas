package com.example.myapplication.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapters.BannerAdapter;

import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.services.FeedService;

import java.util.ArrayList;
import java.util.List;

public class EmAltaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RepositorioObras repositorioObras = RepositorioObras.getInstance();

        View view = inflater.inflate(R.layout.em_alta_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBannerId);

        List<Obra> obras = repositorioObras.emAltaLista;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new BannerAdapter(getActivity(), obras));
        return view;
    }
}