package com.example.myapplication.views;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CardAdapter;
import com.example.myapplication.models.Obra;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.repositories.RepositorioUsuario;
import com.example.myapplication.services.ApplicationService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        TextView nomeUsuario = view.findViewById(R.id.nomeUsuarioId);
        Log.wtf("Gemada", RepositorioUsuario.getInstance().getUsuario().nome);
        nomeUsuario.setText(RepositorioUsuario.getInstance().getUsuario().nome);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewId);
        List<Obra> obras = new ArrayList<Obra>();
        obras.add(new Obra(500, 100, 1, "Magia Rosa", "2", 1, 1, 50, 10, 10,
                "https://media.kitsu.io/anime/45249/poster_image/2ec6301aa7db25544d2f18edd73d23fe.jpg", "10/10/2000",
                "Ribeiro Productions"));
        obras.add(new Obra(501, 100, 1, "Magia Preta", "", 1, 1, 50, 10, 10,
                "https://upload.wikimedia.org/wikipedia/pt/3/3f/OnePunchMan_manga_capa.png", "10/10/2000",
                "Ribeiro Productions"));
        obras.add(new Obra(502, 100, 1, "Magia Magenta", "2", 1, 1, 50, 10, 10,
                "https://upload.wikimedia.org/wikipedia/pt/3/3f/OnePunchMan_manga_capa.png", "10/10/2000",
                "Ribeiro Productions"));
        obras.add(new Obra(503, 100, 1, "Magia Azul", "2", 1, 1, 50, 10, 10,
                "https://upload.wikimedia.org/wikipedia/pt/3/3f/OnePunchMan_manga_capa.png", "10/10/2000",
                "Ribeiro Productions"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(new CardAdapter(getActivity(), obras));

        bottomNavigationView = view.findViewById(R.id.menuListaId);
        EmAltaFragment emAltaFragment = new EmAltaFragment();
        LancamentosFragment lancamentosFragment = new LancamentosFragment();
        EmBreveFragment emBreveFragment = new EmBreveFragment();

        getChildFragmentManager().beginTransaction().replace(R.id.frameLayoutId, emAltaFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.emAltaId:
                        getChildFragmentManager().beginTransaction().setCustomAnimations(
                                R.anim.slide_in, // enter
                                R.anim.fade_out, // exit
                                R.anim.fade_in, // popEnter
                                R.anim.slide_out // popExit
                        ).replace(R.id.frameLayoutId, emAltaFragment).commit();
                        return true;
                    case R.id.lancamentoId:
                        getChildFragmentManager().beginTransaction().setCustomAnimations(
                                R.anim.slide_in, // enter
                                R.anim.fade_out, // exit
                                R.anim.fade_in, // popEnter
                                R.anim.slide_out // popExit
                        ).replace(R.id.frameLayoutId, lancamentosFragment).commit();
                        return true;
                    case R.id.emBreveId:
                        getChildFragmentManager().beginTransaction().setCustomAnimations(
                                R.anim.slide_in, // enter
                                R.anim.fade_out, // exit
                                R.anim.fade_in, // popEnter
                                R.anim.slide_out // popExit
                        ).replace(R.id.frameLayoutId, emBreveFragment).commit();
                        return true;
                }
                return false;
            }
        });
        return view;
    }
}