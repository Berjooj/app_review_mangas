package com.example.myapplication.models;

public class Obra {
    public int id;
    public int idFavorito;
    public int idTipo;
    public String titulo;
    public String subtitulo;
    public int qtEpisodios;
    public int qtVolumes;
    public float qtFavoritos;
    public double nota;
    public int qtAvaliacoes;
    public String urlImagem;
    public String dataLancamento;
    public String criacao;

    public Obra(int id, int idFavorito, int idTipo, String titulo, String subtitulo, int qtEpisodios, int qtVolumes, float qtFavoritos, double nota, int qtAvaliacoes, String urlImagem, String dataLancamento, String criacao) {
        this.id = id;
        this.idFavorito = idFavorito;
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