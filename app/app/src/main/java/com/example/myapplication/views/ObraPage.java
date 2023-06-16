package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class ObraPage extends AppCompatActivity {
    ImageView fundo;
    ImageView card;
    TextView descricao;
    TextView numeroPaginas;
    TextView numeroCurtidas;
    TextView nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obra_page);
        RepositorioObras repositorioObras = RepositorioObras.getInstance();
        int id_obra = getIncomingIntentId();
        Obra obra = repositorioObras.filtro(id_obra);
        Log.wtf("pao de batata", String.valueOf(id_obra));;
        repositorioObras.filtro(id_obra);
        fundo = findViewById(R.id.ImagemFundoId);
        card = findViewById(R.id.imagemPrincipalId);
        descricao = findViewById(R.id.descricaoInfoId);
        numeroPaginas = findViewById(R.id.numeroPaginasId);
        numeroCurtidas = findViewById(R.id.numeroCurtidasId);
        nota = findViewById(R.id.notaId);

        String imagemObra = obra.urlImagem;
        Picasso.get().load(imagemObra).transform(new BlurTransformation(this)).into(fundo);
        Picasso.get().load(imagemObra).into(card);

        descricao.setText(repositorioObras.filtro(id_obra).titulo);

        //numeroPaginas.setText(repositorioObras.filtro(id_obra).qtVolumes);

        //numeroCurtidas.setText(repositorioObras.filtro(id_obra).qtAvaliacoes);

        //nota.setText(new DecimalFormat("#.#").format(repositorioObras.filtro(id_obra).nota) + "/5");

    }
    private int getIncomingIntentId(){
        RepositorioObras repositorioObras = RepositorioObras.getInstance();
        int id = getIntent().getIntExtra("id_obra",0);
        Log.wtf("Feijoada", String.valueOf(id));;
        return id;
    }
}