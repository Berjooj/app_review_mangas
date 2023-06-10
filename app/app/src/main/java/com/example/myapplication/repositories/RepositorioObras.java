package com.example.myapplication.repositories;

import com.example.myapplication.models.Avaliacao;
import com.example.myapplication.models.Obra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RepositorioObras {

    private static RepositorioObras instance;
    protected Map<Integer, Obra> obraMap;

    private RepositorioObras() {
        obraMap = new HashMap<>();
    }

    public static RepositorioObras getInstance() {
        if (instance == null) {
            instance = new RepositorioObras();
        }
        return instance;
    }

    public Map<Integer, Obra> getObras() {
        return this.obraMap;
    }

    public Obra getObraById(int id) {
        return this.obraMap.getOrDefault(id, null);
    }

    public void addObra(int id, Obra obra) {
        obraMap.putIfAbsent(id, obra);
    }
}
