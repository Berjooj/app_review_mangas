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
    private Integer qt_episodios;
    private Integer qt_volumes;
    private Integer qt_favoritos;
    private Double nota;
    private Integer qt_avaliacoes;
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
        if (titulo == null) {
            return "";
        }
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        if (subtitulo == null) {
            return "";
        }
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getData_lancamento() {
        if (data_lancamento == null) {
            return "";
        }
        return data_lancamento;
    }

    public void setData_lancamento(String data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public int getQt_episodios() {
        if (qt_episodios == null) {
            return 0;
        }
        return qt_episodios;
    }

    public void setQt_episodios(Integer qt_episodios) {
        this.qt_episodios = qt_episodios;
    }

    public int getQt_volumes() {
        if (qt_volumes == null) {
            return 0;
        }
        return qt_volumes;
    }

    public void setQt_volumes(Integer qt_volumes) {
        this.qt_volumes = qt_volumes;
    }

    public int getQt_favoritos() {
        if (qt_favoritos == null) {
            return 0;
        }
        return qt_favoritos;
    }

    public void setQt_favoritos(Integer qt_favoritos) {
        this.qt_favoritos = qt_favoritos;
    }

    public double getNota() {
        if (nota == null) {
            return 0.0;
        }
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public int getQt_avaliacoes() {
        if (qt_avaliacoes == null) {
            return 0;
        }
        return qt_avaliacoes;
    }

    public void setQt_avaliacoes(Integer qt_avaliacoes) {
        this.qt_avaliacoes = qt_avaliacoes;
    }

    public String getUrl_imagem() {
        if (url_imagem == null) {
            return "";
        }
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