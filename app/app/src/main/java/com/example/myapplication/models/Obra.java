package com.example.myapplication.models;

public abstract class Obra {
    public int id;
    public int idExterno;
    public int idTipo;
    public String titulo;
    public String subtitulo;
    public int qtEpisodios;
    public int qtVolumes;
    public float qtFavoritos;
    public float nota;
    public int qtAvaliacoes;
    public String urlImagem;
    public String dataLancamento;
    public String criacao;

    public Obra(int id, int idExterno, int idTipo, String titulo, String subtitulo, int qtEpisodios, int qtVolumes, float qtFavoritos, float nota, int qtAvaliacoes, String urlImagem, String dataLancamento, String criacao) {
        this.id = id;
        this.idExterno = idExterno;
        this.idTipo = idTipo;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.qtEpisodios = qtEpisodios;
        this.qtVolumes = qtVolumes;
        this.qtFavoritos = qtFavoritos;
        this.nota = nota;
        this.qtAvaliacoes = qtAvaliacoes;
        this.urlImagem = urlImagem;
        this.dataLancamento = dataLancamento;
        this.criacao = criacao;
    }

    public Obra() {
    }
}