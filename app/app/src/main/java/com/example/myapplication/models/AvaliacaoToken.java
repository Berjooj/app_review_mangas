package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoToken {
    private int id;
    private int id_usuario;
    private int id_obra;
    private double nota;
    private String comentario;
    private String created_at;
    private String updated_at;
    private List<CurtidaToken> curtidas;

    public AvaliacaoToken() {
        this.curtidas = new ArrayList<>();
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_obra() {
        return id_obra;
    }

    public void setId_obra(int id_obra) {
        this.id_obra = id_obra;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<CurtidaToken> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(List<CurtidaToken> curtidas) {
        this.curtidas = curtidas;
    }


}
