package com.example.myapplication.repositories;

import com.example.myapplication.interfaces.SharedObra;
import com.example.myapplication.models.Avaliacao;
import com.example.myapplication.models.Obra;
import com.example.myapplication.services.ApplicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RepositorioObras {

    private static RepositorioObras instance;
    protected ApplicationService appService;
    public ArrayList<Obra> emAltaLista;
    public ArrayList<Obra> emBreveLista;
    public ArrayList<Obra> lancamentoLista;
    public ArrayList<Obra> listaFiltro;

    protected RepositorioObras() {
        this.emAltaLista = new ArrayList<>();
        this.emBreveLista = new ArrayList<>();
        this.lancamentoLista = new ArrayList<>();
        this.listaFiltro = new ArrayList<>();

        this.appService = ApplicationService.getInstance();
    }

    public static RepositorioObras getInstance() {
        if (instance == null) {
            instance = new RepositorioObras();
        }
        return instance;
    }

    public void limparListas() {
        this.emAltaLista = new ArrayList<>();
        this.emBreveLista = new ArrayList<>();
        this.lancamentoLista = new ArrayList<>();
        this.listaFiltro = new ArrayList<>();
    }

    public Obra filtro(int idObra) {
        ArrayList<Obra> obrasTotais = new ArrayList<>();
        obrasTotais.addAll(this.emAltaLista);
        obrasTotais.addAll(this.emBreveLista);
        obrasTotais.addAll(this.lancamentoLista);
        obrasTotais.addAll(this.listaFiltro);

        return obrasTotais.stream().filter(obra -> obra.id == idObra).findFirst().orElse(null);
    }
}
