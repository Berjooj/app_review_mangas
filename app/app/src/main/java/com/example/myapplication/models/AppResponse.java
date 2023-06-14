package com.example.myapplication.models;

public abstract class AppResponse {
    public String mensagem;
    public Object data;
    public int codigo = 500;

    protected AppResponse(String mensagem, int codigo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
    }

    protected AppResponse(String mensagem) {
        this.mensagem = mensagem;
        this.data = data;
    }
}
