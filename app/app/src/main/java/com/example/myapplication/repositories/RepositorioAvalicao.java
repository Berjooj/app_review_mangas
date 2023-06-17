package com.example.myapplication.repositories;

import com.example.myapplication.interfaces.SharedObra;
import com.example.myapplication.models.Avaliacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RepositorioAvalicao implements SharedObra {
    private static RepositorioAvalicao instance;
    public ArrayList<Avaliacao> avaliacaoLista;
    public Avaliacao comentarioUsuarioLogado;

    private RepositorioAvalicao() {
        avaliacaoLista = new ArrayList<>();
        comentarioUsuarioLogado = new Avaliacao();
    }

    public static synchronized RepositorioAvalicao getInstance() {
        if (instance == null) {
            instance = new RepositorioAvalicao();
        }
        return instance;
    }

    @Override
    public void sync() {

    }

    @Override
    public void clear() {
        this.comentarioUsuarioLogado = new Avaliacao();
        this.avaliacaoLista = new ArrayList<>();
    }
}
