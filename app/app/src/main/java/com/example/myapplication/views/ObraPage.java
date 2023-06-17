package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CardAdapter;
import com.example.myapplication.adapters.ComentarioAdapter;
import com.example.myapplication.models.Avaliacao;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioAvalicao;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.services.ApplicationService;
import com.example.myapplication.services.AvaliacaoService;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class ObraPage extends AppCompatActivity {
    ImageView fundo;
    ImageView card;
    TextView descricao;
    TextView numeroPaginas;
    TextView numeroCurtidas;
    TextView nota;
    View publicarLayout;
    View comentario;

    ImageView curtirIcone;
    ImageView imagemIcone;
    TextView nomeUsuarioComentario;
    TextView comentarioText;
    TextView periodoComentario;
    Button publicar;
    TextView comentarioTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obra_page);
        RepositorioObras repositorioObras = RepositorioObras.getInstance();
        RepositorioAvalicao repositorioAvalicao = RepositorioAvalicao.getInstance();
        int id_obra = getIncomingIntentId();
        Obra obra = repositorioObras.filtro(id_obra);
        Log.wtf("pao de batata", String.valueOf(id_obra));
        repositorioObras.filtro(id_obra);
        fundo = findViewById(R.id.ImagemFundoId);
        card = findViewById(R.id.imagemPrincipalId);
        descricao = findViewById(R.id.descricaoInfoId);
        numeroPaginas = findViewById(R.id.numeroPaginasId);
        numeroCurtidas = findViewById(R.id.numeroCurtidasId);
        nota = findViewById(R.id.notaId);
        publicarLayout = findViewById(R.id.publicarLayoutId);
        comentario = findViewById(R.id.layoutComentarioId);
        publicar = findViewById(R.id.publicarId);
        comentarioTextView = findViewById(R.id.comentarioTextViewId);

        publicar.setOnClickListener(view -> {
            String newComentario = comentarioTextView.getText().toString();

            repositorioAvalicao.comentarioUsuarioLogado.comentario = newComentario;
            repositorioAvalicao.comentarioUsuarioLogado.idObra = id_obra;

            Log.wtf("Beijinho", repositorioAvalicao.comentarioUsuarioLogado.comentario);
            Log.wtf("Beijinho", newComentario);
            try {
                AvaliacaoService.criarComentario(onServiceDone -> {
                    ApplicationService service = ApplicationService.getInstance();
                    finish();
                    Intent intentObra = new Intent(service.getContext(), ObraPage.class);
                    intentObra.putExtra("id_obra", id_obra);
                    service.getContext().startActivity(intentObra);
                }, onError -> {
                    Log.wtf("Goiabinha", onError.mensagem);
                });
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

        if (obra.urlImagem != null) {
            String imagemObra = obra.urlImagem;
            Picasso.get().load(imagemObra).resize(1600, 2272).onlyScaleDown().transform(new BlurTransformation(this)).into(fundo);
            Picasso.get().load(imagemObra).resize(1600, 2272).onlyScaleDown().into(card);
        } else {
            card.setImageResource(R.drawable.blank);
        }
        if (repositorioAvalicao.comentarioUsuarioLogado != null && repositorioAvalicao.comentarioUsuarioLogado.created_at != null) {
            publicarLayout.setVisibility(View.GONE);
            comentario.setVisibility(View.VISIBLE);
            Avaliacao avaliacao = repositorioAvalicao.comentarioUsuarioLogado;
            //nomeUsuarioComentario.setText(repositorioAvalicao.comentarioUsuarioLogado.nome);
            comentarioText.setText(avaliacao.comentario);
            LocalDate dataComentario = LocalDate.parse((avaliacao.created_at).substring(0, 10));
            LocalDate dataAtual = LocalDate.now();
            Period periodo = Period.between(dataComentario, dataAtual);
            String periodoFormatado;
            if (periodo.getYears() >= 1) {
                periodoFormatado = "Há " + periodo.getYears() + " ano/s";
            } else if (periodo.getMonths() >= 1) {
                periodoFormatado = "Há " + periodo.getMonths() + " meses";
            } else if (periodo.getDays() >= 31) {
                int semanas = periodo.getDays() / 7;
                periodoFormatado = "Há " + semanas + " semana/s";
            } else {
                periodoFormatado = "Há " + periodo.getDays() + " dias";
            }
            periodoComentario.setText(periodoFormatado);
        }


        descricao.setText(repositorioObras.filtro(id_obra).titulo);

        numeroPaginas.setText(String.valueOf(repositorioObras.filtro(id_obra).qtVolumes));

        numeroCurtidas.setText(String.valueOf(repositorioObras.filtro(id_obra).qtAvaliacoes));

        nota.setText(new DecimalFormat("#.#").format(repositorioObras.filtro(id_obra).nota) + "/5");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewComentariosId);
        List<Avaliacao> avaliacoes = repositorioAvalicao.avaliacaoLista;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(new ComentarioAdapter(this, avaliacoes));
    }

    private int getIncomingIntentId() {
        RepositorioObras repositorioObras = RepositorioObras.getInstance();
        int id = getIntent().getIntExtra("id_obra", 0);
        Log.wtf("Feijoada", String.valueOf(id));
        return id;
    }
}