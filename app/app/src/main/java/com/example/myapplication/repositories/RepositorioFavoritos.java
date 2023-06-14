package com.example.myapplication.repositories;

import com.example.myapplication.models.Obra;

import java.util.HashMap;
import java.util.Map;

public class RepositorioFavoritos extends RepositorioObras {

    private static RepositorioFavoritos instance;

    private RepositorioFavoritos() {
        super();
    }

    public static synchronized RepositorioFavoritos getInstance() {
        if (instance == null) {
            instance = new RepositorioFavoritos();
        }
        return instance;
    }

    public void removeObraFavoritos(int id, Obra obra) {
        //TODO: Verificar se esse treco funciona
        this.obraMap.computeIfPresent(id, (k, v) -> this.obraMap.remove(k));
    }
}
