package com.yaros.frasesYaros.models;

public class Frase {
    private int id;
    private int idAutor;
    private int idCategoria;
    private String texto;
    private String fechaProgramada;

    public Frase(int id, int idAutor, int idCategoria, String texto, String fechaProgramada) {
        this.id = id;
        this.idAutor = idAutor;
        this.idCategoria = idCategoria;
        this.texto = texto;
        this.fechaProgramada = fechaProgramada;
    }

    public int getId() {
        return id;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getTexto() {
        return texto;
    }

    public String getFechaProgramada() {
        return fechaProgramada;
    }

    @Override
    public String toString() {
        return "Frase{" +
                "id=" + id +
                ", idAutor=" + idAutor +
                ", idCategoria=" + idCategoria +
                ", texto='" + texto + '\'' +
                ", fechaProgramada='" + fechaProgramada + '\'' +
                '}';
    }
}
