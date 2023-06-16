package com.example.myapplication.services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.interfaces.ServiceDone;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.google.gson.Gson;

import org.json.JSONException;


public class ApplicationService {

    private static ApplicationService instance;

    private Context context;

    public Gson gson;

    private ApplicationService() {
        this.gson = new Gson();
    }

    public static synchronized ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }
        return instance;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void initApp(ServiceDone callback) {
        RepositorioFavoritos repositorioFavoritos = RepositorioFavoritos.getInstance();

        if (repositorioFavoritos.obraLista.size() == 0) {
            FavoritoService.getFavoritos(
                    onSuccess -> {
                        FeedService.carregaFeedCompleto(callback);
                    },
                    onError -> {
                        Toast.makeText(this.context, "Erro ao carregar a lista de favoritos", Toast.LENGTH_SHORT).show();
                    }
            );
        } else {
            FeedService.carregaFeedCompleto(callback);
        }
    }
}

