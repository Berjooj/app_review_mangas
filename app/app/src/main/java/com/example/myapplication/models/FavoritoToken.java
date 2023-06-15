package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class FavoritoToken {
    private int id;

    @SerializedName("id_usuario")
    private int userId;

    @SerializedName("id_obra")
    private int obraId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    private Obra obra;

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getObraId() {
        return obraId;
    }

    public void setObraId(int obraId) {
        this.obraId = obraId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    // Classe interna Obra

    public static class Obra {
        private int id;

        @SerializedName("id_tipo")
        private int tipoId;

        private String titulo;

        private String subtitulo;

        @SerializedName("qt_episodios")
        private int quantidadeEpisodios;

        @SerializedName("qt_volumes")
        private int quantidadeVolumes;

        @SerializedName("qt_avaliacoes")
        private int quantidadeAvaliacoes;

        @SerializedName("qt_favoritos")
        private int quantidadeFavoritos;

        private double nota;

        @SerializedName("url_imagem")
        private String urlImagem;

        @SerializedName("data_lancamento")
        private String dataLancamento;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("updated_at")
        private String updatedAt;

        @SerializedName("deleted_at")
        private String deletedAt;

        // Getters e Setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTipoId() {
            return tipoId;
        }

        public void setTipoId(int tipoId) {
            this.tipoId = tipoId;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getSubtitulo() {
            return subtitulo;
        }

        public void setSubtitulo(String subtitulo) {
            this.subtitulo = subtitulo;
        }

        public int getQuantidadeEpisodios() {
            return quantidadeEpisodios;
        }

        public void setQuantidadeEpisodios(int quantidadeEpisodios) {
            this.quantidadeEpisodios = quantidadeEpisodios;
        }

        public int getQuantidadeVolumes() {
            return quantidadeVolumes;
        }

        public void setQuantidadeVolumes(int quantidadeVolumes) {
            this.quantidadeVolumes = quantidadeVolumes;
        }

        public int getQuantidadeAvaliacoes() {
            return quantidadeAvaliacoes;
        }

        public void setQuantidadeAvaliacoes(int quantidadeAvaliacoes) {
            this.quantidadeAvaliacoes = quantidadeAvaliacoes;
        }

        public int getQuantidadeFavoritos() {
            return quantidadeFavoritos;
        }

        public void setQuantidadeFavoritos(int quantidadeFavoritos) {
            this.quantidadeFavoritos = quantidadeFavoritos;
        }

        public double getNota() {
            return nota;
        }

        public void setNota(double nota) {
            this.nota = nota;
        }

        public String getUrlImagem() {
            return urlImagem;
        }

        public void setUrlImagem(String urlImagem) {
            this.urlImagem = urlImagem;
        }

        public String getDataLancamento() {
            return dataLancamento;
        }

        public void setDataLancamento(String dataLancamento) {
            this.dataLancamento = dataLancamento;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(String deletedAt) {
            this.deletedAt = deletedAt;
        }
    }
}