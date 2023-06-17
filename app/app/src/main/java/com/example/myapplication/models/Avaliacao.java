package com.example.myapplication.models;

public class Avaliacao {
    public int id;
    public int idUsuario;
    public int idObra;
    public double nota;
    public String comentario;
    public int qtCurtidas = 0;
    public boolean curtiu = false;
    public String nome;
    public String created_at;

    public Avaliacao(int id, int idUsuario, int idObra, double nota, String comentario, int qtCurtidas, boolean curtiu,String nome,String created_at) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idObra = idObra;
        this.nota = nota;
        this.comentario = comentario;
        this.qtCurtidas = qtCurtidas;
        this.curtiu = curtiu;
        this.nome = nome;
        this.created_at = created_at;
    }

    public Avaliacao() {
    }
}