package com.example.myapplication.repositories;

import com.example.myapplication.interfaces.SharedObra;
import com.example.myapplication.models.Avaliacao;
import com.example.myapplication.models.Obra;
import com.example.myapplication.services.ApplicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RepositorioObras implements SharedObra {

    private static RepositorioObras instance;
    protected ApplicationService appService;
    protected ArrayList<Obra> obraLista;

    protected RepositorioObras() {
        this.obraLista = new ArrayList<>();

        this.appService = ApplicationService.getInstance();
    }

    public static RepositorioObras getInstance() {
        if (instance == null) {
            instance = new RepositorioObras();
        }
        return instance;
    }

    public ArrayList<Obra> getObras() {
        return this.obraLista;
    }

    public Obra getObraById(int id) {
        return obraLista.stream().filter(obraExistente -> obraExistente.id == id).findFirst().orElse(null);
    }

    public void addObra(Obra obra) {
        if (obraLista.stream().filter(obraExistente -> obraExistente.id == obra.id).findFirst().orElse(null) == null) {
            this.obraLista.add(obra);
        }
    }

    @Override
    public void sync() {

    }

    @Override
    public void clear() {

    }
}
