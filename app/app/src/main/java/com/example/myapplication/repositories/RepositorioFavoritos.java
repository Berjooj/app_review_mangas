package com.example.myapplication.repositories;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.interfaces.SharedObra;
import com.example.myapplication.models.Obra;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.services.ApplicationService;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RepositorioFavoritos implements SharedObra {

    private static RepositorioFavoritos instance;
    protected ApplicationService appService;
    public ArrayList<Obra> obraLista;

    private RepositorioFavoritos() {
        this.appService = ApplicationService.getInstance();
        this.obraLista = new ArrayList<>();

        SharedPreferences pref = this.appService.getContext().getSharedPreferences("AuthUser", MODE_PRIVATE);

        if (pref.contains("FavList")) {
            try {
                this.obraLista = appService.gson.fromJson(
                        pref.getString("FavList", null),
                        new TypeToken<ArrayList<Obra>>() {
                        }.getType()
                );
            } catch (Exception exception) {
                Toast.makeText(this.appService.getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("UserData", exception.getMessage());
            }
        }
    }

    public static synchronized RepositorioFavoritos getInstance() {
        if (instance == null) {
            instance = new RepositorioFavoritos();
        }
        return instance;
    }

    public void removeObraFavoritos(int idFavorito) {
        this.obraLista.removeIf(obra -> obra.idFavorito == idFavorito);
        this.sync();
    }

    @Override
    public void sync() {
        SharedPreferences pref = this.appService.getContext().getSharedPreferences("AuthUser", MODE_PRIVATE);

        pref.edit().putString("FavList", this.appService.gson.toJson(this.obraLista)).apply();
    }

    @Override
    public void clear() {
        this.obraLista = new ArrayList<>();

        SharedPreferences pref = this.appService.getContext().getSharedPreferences("AuthUser", MODE_PRIVATE);

        pref.edit().remove("FavList").apply();
    }
}
