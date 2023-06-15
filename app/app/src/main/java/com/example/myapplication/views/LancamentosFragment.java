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
import com.example.myapplication.models.Manga;
import com.example.myapplication.models.Obra;

import java.util.ArrayList;
import java.util.List;


public class LancamentosFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.lancamentos_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBannerId);

        List<Obra> obras = new ArrayList<Obra>();
        obras.add(new Manga(500, 100, 1, "Ovo Rosa", "2", 1, 1, 50, 10, 10, "https://media.kitsu.io/anime/45249/poster_image/2ec6301aa7db25544d2f18edd73d23fe.jpg", "10/10/2000", "Ribeiro Productions"));
        obras.add(new Manga(501, 100, 1, "Ovo Preta", "", 1, 1, 50, 10, 10, "https://upload.wikimedia.org/wikipedia/pt/3/3f/OnePunchMan_manga_capa.png", "10/10/2000", "Ribeiro Productions"));
        obras.add(new Manga(502, 100, 1, "Ovo Magenta", "2", 1, 1, 50, 10, 10, "https://upload.wikimedia.org/wikipedia/pt/3/3f/OnePunchMan_manga_capa.png", "10/10/2000", "Ribeiro Productions"));
        obras.add(new Manga(503, 100, 1, "Ovo Azul", "2", 1, 1, 50, 10, 10, "https://upload.wikimedia.org/wikipedia/pt/3/3f/OnePunchMan_manga_capa.png", "10/10/2000", "Ribeiro Productions"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new BannerAdapter(getActivity(), obras));
        return view;
    }
}