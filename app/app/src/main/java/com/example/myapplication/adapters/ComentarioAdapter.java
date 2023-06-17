package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Avaliacao;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioAvalicao;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.repositories.RepositorioUsuario;
import com.example.myapplication.services.FavoritoService;
import com.example.myapplication.views.ObraPage;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;


public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioAdapter.ViewHolder> {

    private List<Avaliacao> avaliacoes;

    public Context context;


    public ComentarioAdapter(Context context, List<Avaliacao> avaliacoes) {
        this.context = context;
        this.avaliacoes = avaliacoes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_holder_comentario, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioAdapter.ViewHolder holder, int position) {
        RepositorioFavoritos repositorioFavoritos = RepositorioFavoritos.getInstance();
        RepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        RepositorioObras repositorioObras = RepositorioObras.getInstance();
        RepositorioAvalicao repositorioAvalicao = RepositorioAvalicao.getInstance();
        FavoritoService favoritoService;

        Avaliacao avaliacao = repositorioAvalicao.avaliacaoLista.get(position);
        holder.nomeUsuarioComentario.setText(avaliacao.nome);
        holder.comentario.setText(avaliacao.comentario);
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
        holder.periodoComentario.setText(periodoFormatado);
    }

    @Override
    public int getItemCount() {
        return avaliacoes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView curtirIcone;
        ImageView imagemIcone;
        TextView nomeUsuarioComentario;
        TextView comentario;
        TextView periodoComentario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            curtirIcone = itemView.findViewById(R.id.curtirIconeId);
            imagemIcone = itemView.findViewById(R.id.imagemIconeId);
            nomeUsuarioComentario = itemView.findViewById(R.id.nomeUsuarioComentarioId);
            comentario = itemView.findViewById(R.id.comentarioId);
            periodoComentario = itemView.findViewById(R.id.periodoComentarioId);
        }
    }

}
