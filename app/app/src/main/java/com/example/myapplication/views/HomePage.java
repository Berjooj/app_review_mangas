package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CardAdapter;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomePage extends AppCompatActivity {

    public RepositorioObras repoObras;
    public RepositorioFavoritos repoFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.repoObras = RepositorioObras.getInstance();
        this.repoFavoritos = RepositorioFavoritos.getInstance();

        Map<Integer ,Obra> favoritos = this.repoFavoritos.getObras();
        setContentView(R.layout.home_page);

        ImageButton voltarActivity = findViewById(R.id.voltarId);
        voltarActivity.setOnClickListener(view -> finish());

        RecyclerView recyclerView = findViewById(R.id.recyclerViewId);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(getApplicationContext(),favoritos));
    }
}