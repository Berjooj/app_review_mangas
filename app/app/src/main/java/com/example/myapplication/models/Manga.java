package com.example.myapplication.models;

public class Manga extends Obra{
    public Manga(int id, int idExterno, int idTipo, String titulo, String subtitulo, int qtEpisodios, int qtVolumes, float qtFavoritos, float nota, int qtAvaliacoes, String urlImagem, String dataLancamento, String criacao) {
        super(id, idExterno, idTipo, titulo, subtitulo, qtEpisodios, qtVolumes, qtFavoritos, nota, qtAvaliacoes, urlImagem, dataLancamento, criacao);
    }
}
