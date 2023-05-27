package com.example.myapplication.repositories;

public class RepositorioObras {

    private static RepositorioObras instance;

    public RepositorioObras() {
    }

    public static RepositorioObras getInstance() {
        if (instance == null) {
            instance = new RepositorioObras();
        }
        return instance;
    }
}
