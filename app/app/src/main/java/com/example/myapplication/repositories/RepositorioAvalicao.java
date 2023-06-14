package com.example.myapplication.repositories;

import com.example.myapplication.models.Avaliacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RepositorioAvalicao {
    private static RepositorioAvalicao instance;

    // Lista de Avaliações em que a Key é a Obra e a Avalicao é o valor
    private Map<Integer, ArrayList<Avaliacao>> avaliacaoMap;

    private RepositorioAvalicao() {
        avaliacaoMap = new HashMap<>();
    }

    public static synchronized RepositorioAvalicao getInstance() {
        if (instance == null) {
            instance = new RepositorioAvalicao();
        }
        return instance;
    }

    // Metodo de adicionar avaliação em uma Obra
    public void addAvaliacao(int idObra, Avaliacao avaliacao) {
        if (!this.avaliacaoMap.containsKey(idObra)) {
            this.avaliacaoMap.put(idObra, new ArrayList<>());
        }
        this.avaliacaoMap.get(idObra).add(avaliacao);
    }

    // Metodo de buscar todos as avaliações de uma Obra
    public ArrayList<Avaliacao> getAvaliacoesByIdObra(int idObra) {
        return this.avaliacaoMap.getOrDefault(idObra, null);
    }
}
