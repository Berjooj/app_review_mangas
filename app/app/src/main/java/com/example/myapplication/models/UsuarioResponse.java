package com.example.myapplication.models;

public class UsuarioResponse extends AppResponse {
    public String mensagem;
    public Usuario data;
    public int codigo = 500;

    public UsuarioResponse(String mensagem, int codigo, Usuario usuario) {
        super(mensagem, codigo);

        this.mensagem = mensagem;
        this.codigo = codigo;
        this.data = usuario;
    }

    public UsuarioResponse(String mensagem, Usuario usuario) {
        super(mensagem);
        this.mensagem = mensagem;
        this.data = usuario;
    }
}
