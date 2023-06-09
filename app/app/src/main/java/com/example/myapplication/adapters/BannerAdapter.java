package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.services.ApplicationService;
import com.example.myapplication.services.AvaliacaoService;
import com.example.myapplication.services.FavoritoService;
import com.example.myapplication.views.ObraPage;
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

        int posicao = position;

        holder.nomeObra.setText(obras.get(position).titulo);
        holder.criadorObra.setText(obras.get(position).subtitulo);
        if (obras.get(position).subtitulo != null) {
            Log.wtf("TAG", obras.get(position).subtitulo);
            holder.criadorObra.setText(obras.get(position).subtitulo);
        }
        if (obras.get(position).favoritada)
            holder.favoritosBotao.setColorFilter(ContextCompat.getColor(context, R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
        else
            holder.favoritosBotao.setColorFilter(ContextCompat.getColor(context, R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);

        holder.numPaginas.setText(String.valueOf(obras.get(position).qtVolumes));
        holder.numCurtidas.setText(String.valueOf(obras.get(position).qtAvaliacoes));
        holder.numNota.setText(String.valueOf(obras.get(position).nota));

        if (!obras.get(position).categorias.isEmpty() && obras.get(position).categorias.size() > 2) {
            String categorias = "Categorias: " + obras.get(position).categorias.get(0) + ", " + obras.get(position).categorias.get(1);
            holder.categorias.setText(String.valueOf(categorias));
        }

        String imageUrl = obras.get(position).urlImagem;
        Picasso.get().load(imageUrl).resize(1600, 2272).onlyScaleDown().into(holder.bannerImagem);

        holder.bannerImagem.setOnClickListener(view -> {

            AvaliacaoService.buscarComentarios(obras.get(position).id, onServiceDone -> {
                Intent intentObra = new Intent(context, ObraPage.class);
                intentObra.putExtra("id_obra", obras.get(position).id);
                context.startActivity(intentObra);
            }, null);
        });

        holder.favoritosBotao.setOnClickListener(v -> {
            try {
                ApplicationService applicationService = ApplicationService.getInstance();

                CardAdapter cardAdapter = applicationService.cardAdapter;
                if (obras.get(position).favoritada) {
                    Obra obra = repositorioFavoritos.obraLista.stream().filter(obraFavoritada ->
                            obraFavoritada.id == obras.get(position).id).findFirst().orElse(null);

                    if (obra != null) {
                        applicationService.loader.showDialog();

                        FavoritoService.removerFavoritos(obra.idFavorito, obras.get(posicao).id,
                                onSuccess -> {
                                    FavoritoService.getFavoritos(success -> {
                                        applicationService.loader.dismiss();

                                        if (cardAdapter != null)
                                            cardAdapter.notifyDataSetChanged();
                                        holder.favoritosBotao.setColorFilter(ContextCompat.getColor(context, R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
                                        Log.wtf("Biscoito", "deu bom (removeu)");
                                        Log.wtf("bolacha", String.valueOf(repositorioFavoritos.obraLista.size()));
                                    }, error -> {
                                        applicationService.loader.dismiss();
                                    });
                                },
                                onError -> {
                                    applicationService.loader.dismiss();

                                    holder.favoritosBotao.setColorFilter(ContextCompat.getColor(context, R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
                                    Log.wtf("Biscoito", "deu ruim");
                                }
                        );
                    }
                } else {
                    applicationService.loader.showDialog();

                    FavoritoService.addFavoritos(obras.get(posicao).id,
                            onSuccess -> {
                                FavoritoService.getFavoritos(success -> {
                                    applicationService.loader.dismiss();

                                    if (cardAdapter != null)
                                        cardAdapter.notifyDataSetChanged();
                                    holder.favoritosBotao.setColorFilter(ContextCompat.getColor(context, R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
                                    Log.wtf("bolacha", "deu bom (adicionou)");
                                    Log.wtf("bolacha", String.valueOf(repositorioFavoritos.obraLista.size()));
                                }, error -> {
                                    applicationService.loader.dismiss();
                                });
                            },
                            onError -> {
                                applicationService.loader.dismiss();

                                holder.favoritosBotao.setColorFilter(ContextCompat.getColor(context, R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
                                Log.wtf("bolacha", "deu ruim");
                            }
                    );
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
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
        TextView categorias;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favoritosBotao = itemView.findViewById(R.id.favoritosId);
            bannerImagem = itemView.findViewById(R.id.cardViewId);
            nomeObra = itemView.findViewById(R.id.nomeObraId);
            criadorObra = itemView.findViewById(R.id.criadorObraId);
            numPaginas = itemView.findViewById(R.id.numPaginasId);
            numCurtidas = itemView.findViewById(R.id.numCurtidasId);
            numNota = itemView.findViewById(R.id.numNotaId);
            categorias = itemView.findViewById(R.id.categorias);
        }
    }

}
