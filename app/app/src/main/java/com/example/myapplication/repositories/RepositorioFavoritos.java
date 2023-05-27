package com.example.myapplication.repositories;

public class RepositorioFavoritos extends RepositorioObras {

    private static RepositorioFavoritos instance;

    public RepositorioFavoritos() {
    }

    public static RepositorioFavoritos getInstance() {
        if (instance == null) {
            instance = new RepositorioFavoritos();
        }
        return instance;
    }
}
