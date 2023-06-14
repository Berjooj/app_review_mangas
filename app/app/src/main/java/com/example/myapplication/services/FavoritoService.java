package com.example.myapplication.services;

import com.example.myapplication.models.Obra;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioUsuario;

import java.util.ArrayList;
import java.util.Map;

public class FavoritoService {

    // Get favs -> filtro opt
    public static Map<Integer, Obra> getFavoritos() {
        RepositorioFavoritos repoFavoritos = RepositorioFavoritos.getInstance();
        RepositorioUsuario repoUsuario = RepositorioUsuario.getInstance();

        Map<Integer, Obra> favoritos = repoFavoritos.getObras();
        Usuario usuario = repoUsuario.getUsuario();

        if (favoritos.size() == 0) {
            // Fetch favoritos
        }

        return favoritos;
    }

    // Deletar fav

    // Add fav
}
