package com.example.myapplication.adapters;

import android.content.Context;
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
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioObras;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Obra> obras;

    public Context context;



    public CardAdapter(Context context, List<Obra> obras) {
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
        //Obra obra = Objects.requireNonNull(obras.get((obras.keySet().toArray())[position]));
        Log.wtf("Banana", obras.get(position).titulo );
        String titulo = obras.get(position).titulo;
        if (titulo.length() > 25) {
            titulo = titulo.substring(0, 25);
        }
        holder.textView.setText(titulo);
        String imageUrl = obras.get(position).urlImagem;
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
