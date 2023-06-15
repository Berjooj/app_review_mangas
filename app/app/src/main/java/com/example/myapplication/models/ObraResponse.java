package com.example.myapplication.models;

import java.util.ArrayList;

public class ObraResponse extends AppResponse {
    public String mensagem;
    public ArrayList<Obra> data;
    public int codigo = 500;

    public ObraResponse(String mensagem, int codigo, ArrayList<Obra> obras) {
        super(mensagem, codigo);

        this.mensagem = mensagem;
        this.codigo = codigo;
        this.data = obras;
    }

    public ObraResponse(String mensagem, ArrayList<Obra> obras) {
        super(mensagem);
        this.mensagem = mensagem;
        this.data = obras;
    }
}
