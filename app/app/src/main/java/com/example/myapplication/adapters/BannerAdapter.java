package com.example.myapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.services.FavoritoService;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.List;


public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    private List<Obra> obras;

    public Context context;


    public BannerAdapter(Context context, List<Obra> obras) {
        this.context = context;
        this.obras = obras;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_holder_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.ViewHolder holder, int position) {
        RepositorioFavoritos repositorioFavoritos = RepositorioFavoritos.getInstance();
        FavoritoService favoritoService;
        int posicao = position;
        Log.wtf("Banana", obras.get(position).titulo);
        holder.nomeObra.setText(obras.get(position).titulo);
        holder.criadorObra.setText(obras.get(position).subtitulo);
        holder.numPaginas.setText(String.valueOf(obras.get(position).qtVolumes));
        holder.numCurtidas.setText(String.valueOf(obras.get(position).qtAvaliacoes));
        holder.numNota.setText(String.valueOf(obras.get(position).nota));
        String imageUrl = obras.get(position).urlImagem;
        Picasso.get().load(imageUrl).into(holder.bannerImagem);

        holder.favoritosBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FavoritoService.addFavoritos(obras.get(posicao).id,
                            onSuccess -> {
                                holder.favoritosBotao.setColorFilter(R.color.azul);
                                Log.wtf("bolacha", "deu bom");
                            },
                            onError -> {
                                holder.favoritosBotao.setColorFilter(R.color.amarelo);
                                Log.wtf("bolacha", "deu ruim");

                            }
                    );
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return obras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bannerImagem;
        ImageView favoritosBotao;
        TextView nomeObra;
        TextView criadorObra;
        TextView numPaginas;
        TextView numCurtidas;
        TextView numNota;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favoritosBotao = itemView.findViewById(R.id.favoritosId);
            bannerImagem = itemView.findViewById(R.id.cardViewId);
            nomeObra = itemView.findViewById(R.id.nomeObraId);
            criadorObra = itemView.findViewById(R.id.criadorObraId);
            numPaginas = itemView.findViewById(R.id.numPaginasId);
            numCurtidas = itemView.findViewById(R.id.numCurtidasId);
            numNota = itemView.findViewById(R.id.numNotaId);
        }
    }

}
