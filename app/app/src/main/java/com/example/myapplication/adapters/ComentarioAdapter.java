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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Avaliacao;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioAvalicao;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.repositories.RepositorioUsuario;
import com.example.myapplication.services.ApplicationService;
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

        Log.wtf("Amendoim", String.valueOf(avaliacao.nota));

        ApplicationService service = ApplicationService.getInstance();

        holder.estrelaUmId.setColorFilter(ContextCompat.getColor(service.getContext(), avaliacao.nota >= 1 ? R.color.amarelo : R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
        holder.estrelaDoisId.setColorFilter(ContextCompat.getColor(service.getContext(), avaliacao.nota >= 2 ? R.color.amarelo : R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
        holder.estrelaTresId.setColorFilter(ContextCompat.getColor(service.getContext(), avaliacao.nota >= 3 ? R.color.amarelo : R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
        holder.estrelaQuatroId.setColorFilter(ContextCompat.getColor(service.getContext(), avaliacao.nota >= 4 ? R.color.amarelo : R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
        holder.estrelaCincoId.setColorFilter(ContextCompat.getColor(service.getContext(), avaliacao.nota >= 5
                ? R.color.amarelo : R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);

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

        ImageView estrelaUmId;
        ImageView estrelaDoisId;
        ImageView estrelaTresId;
        ImageView estrelaQuatroId;
        ImageView estrelaCincoId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            curtirIcone = itemView.findViewById(R.id.curtirIconeId);
            imagemIcone = itemView.findViewById(R.id.imagemIconeId);
            nomeUsuarioComentario = itemView.findViewById(R.id.nomeUsuarioComentarioId);
            comentario = itemView.findViewById(R.id.comentarioId);
            periodoComentario = itemView.findViewById(R.id.periodoComentarioId);

            estrelaUmId = itemView.findViewById(R.id.estrelaUmId2);
            estrelaDoisId = itemView.findViewById(R.id.estrelaDoisId2);
            estrelaTresId = itemView.findViewById(R.id.estrelaTresId2);
            estrelaQuatroId = itemView.findViewById(R.id.estrelaQuatroId2);
            estrelaCincoId = itemView.findViewById(R.id.estrelaCincoId2);
        }
    }

}
