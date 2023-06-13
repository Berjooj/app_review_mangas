package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CardAdapter;
import com.example.myapplication.models.Obra;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.repositories.RepositorioUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomePage extends AppCompatActivity {

    public RepositorioObras repoObras;
    public RepositorioFavoritos repoFavoritos;

    TextView nomeUsuario;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.repoObras = RepositorioObras.getInstance();
        this.repoFavoritos = RepositorioFavoritos.getInstance();

        pref = getSharedPreferences("MyPreferencias",MODE_PRIVATE);
        editor = pref.edit();
        Map<Integer, Obra> favoritos = this.repoFavoritos.getObras();
        setContentView(R.layout.home_page);

        TextView nomeUsuario = findViewById(R.id.nomeUsuarioId);
        nomeUsuario.setText(pref.getString("nome",null));
        Button voltarActivity = findViewById(R.id.logoutId);
        voltarActivity.setOnClickListener(view ->{
            editor.clear();
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), LandingPage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewId);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(getApplicationContext(), favoritos));
    }
}