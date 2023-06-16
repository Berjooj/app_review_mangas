package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;

public class ObraPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obra_page);
        RepositorioObras repositorioObras = RepositorioObras.getInstance();
    }
    private void getIncomingIntent(){
        RepositorioObras repositorioObras = RepositorioObras.getInstance();
        Intent i = getIntent();
        int id = Integer.parseInt(i.getStringExtra("id_obra"));
        repositorioObras.filtro(id);

    }
}