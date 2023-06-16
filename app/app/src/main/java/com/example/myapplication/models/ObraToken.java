package com.example.myapplication.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ObraToken {
    private int id_tipo;
    private String updated_at;
    private String created_at;
    private int id;
    private String titulo;
    private String subtitulo;
    private String data_lancamento;
    private int qt_episodios;
    private int qt_volumes;
    private int qt_favoritos;
    private double nota;
    private int qt_avaliacoes;
    private String url_imagem;
    private ArrayList<CategoriaToken> categorias;

    public ObraToken() {
        this.categorias = new ArrayList<>();
    }

    // Getters e Setters

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(String data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public int getQt_episodios() {
        return qt_episodios;
    }

    public void setQt_episodios(int qt_episodios) {
        this.qt_episodios = qt_episodios;
    }

    public int getQt_volumes() {
        return qt_volumes;
    }

    public void setQt_volumes(int qt_volumes) {
        this.qt_volumes = qt_volumes;
    }

    public int getQt_favoritos() {
        return qt_favoritos;
    }

    public void setQt_favoritos(int qt_favoritos) {
        this.qt_favoritos = qt_favoritos;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getQt_avaliacoes() {
        return qt_avaliacoes;
    }

    public void setQt_avaliacoes(int qt_avaliacoes) {
        this.qt_avaliacoes = qt_avaliacoes;
    }

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }

    public ArrayList<CategoriaToken> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<CategoriaToken> categorias) {
        this.categorias = categorias;
    }
}