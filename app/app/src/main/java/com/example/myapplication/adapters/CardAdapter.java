package com.example.myapplication.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioObras;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private Map<Integer, Obra> obras;

    public Context context;



    public CardAdapter(Context context, Map<Integer, Obra> obras) {
        this.context = context;
        this.obras = obras;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_holder_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        Obra obra = Objects.requireNonNull(obras.get((obras.keySet().toArray())[position]));
        holder.textView.setText(obra.titulo);
        String imageUrl = obra.urlImagem;
        Picasso.get().load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return obras.size();
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
