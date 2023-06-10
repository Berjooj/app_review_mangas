package com.example.myapplication.models;

import java.util.ArrayList;

public class Usuario {
    public int id;
    public String nome;
    public String email;
    public String password;
    public int idFotoPerfil;
    public String token;
    public ArrayList<String> categorias;

    public Usuario(int id, String nome, String email, String password, int idFotoPerfil, String token) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.idFotoPerfil = idFotoPerfil;
        this.token = token;
    }

    public Usuario() {
    }
}
