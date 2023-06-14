package com.example.myapplication.views;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CardAdapter;
import com.example.myapplication.models.Manga;
import com.example.myapplication.models.Obra;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.services.ApplicationService;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        TextView nomeUsuario = view.findViewById(R.id.nomeUsuarioId);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewId);
        List<Obra> obras = new ArrayList<Obra>();
        obras.add(new Manga(500, 100, 1, "Magia Rosa", "2", 1, 1, 50, 10, 10, "https://upload.wikimedia.org/wikipedia/pt/3/3f/OnePunchMan_manga_capa.png", "10/10/2000", "Ribeiro Productions"));
        obras.add(new Manga(501, 100, 1, "Magia Preta", "", 1, 1, 50, 10, 10, "https://upload.wikimedia.org/wikipedia/pt/3/3f/OnePunchMan_manga_capa.png", "10/10/2000", "Ribeiro Productions"));
        obras.add(new Manga(502, 100, 1, "Magia Magenta", "2", 1, 1, 50, 10, 10, "https://upload.wikimedia.org/wikipedia/pt/3/3f/OnePunchMan_manga_capa.png", "10/10/2000", "Ribeiro Productions"));
        obras.add(new Manga(503, 100, 1, "Magia Azul", "2", 1, 1, 50, 10, 10, "https://upload.wikimedia.org/wikipedia/pt/3/3f/OnePunchMan_manga_capa.png", "10/10/2000", "Ribeiro Productions"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new CardAdapter(getActivity(), obras));
        return view;
    }
}