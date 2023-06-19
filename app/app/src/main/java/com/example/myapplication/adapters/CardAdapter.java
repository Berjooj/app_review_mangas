package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.myapplication.services.ApplicationService;
import com.example.myapplication.services.AvaliacaoService;
import com.example.myapplication.views.Cadastro;
import com.example.myapplication.views.ObraPage;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Obra> obras;

    public Context context;


    public CardAdapter(Context context, List<Obra> obras) {
        ApplicationService applicationService = ApplicationService.getInstance();
        applicationService.cardAdapter = this;

        this.context = context;
        this.obras = RepositorioFavoritos.getInstance().obraLista;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_holder_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        //Obra obra = Objects.requireNonNull(obras.get((obras.keySet().toArray())[position]));
        if (RepositorioFavoritos.getInstance().obraLista.get(position) != null) {
            Log.wtf("Banana", obras.get(position).titulo);
            holder.textView.setText(obras.get(position).titulo);
            String imageUrl = obras.get(position).urlImagem;
            Picasso.get().load(imageUrl).resize(1600, 2272).onlyScaleDown().into(holder.imageView);

            holder.imageView.setOnClickListener(view -> {
                AvaliacaoService.buscarComentarios(obras.get(position).id, onServiceDone -> {
                    Intent intentObra = new Intent(context, ObraPage.class);
                    intentObra.putExtra("id_obra", obras.get(position).id);
                    context.startActivity(intentObra);
                }, null);
            });
        }
    }

    @Override
    public int getItemCount() {
        return RepositorioFavoritos.getInstance().obraLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardImagemId);
            textView = itemView.findViewById(R.id.textoId);
        }
    }

}
