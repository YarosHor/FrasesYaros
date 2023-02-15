package com.yaros.frasesYaros.models;

import androidx.annotation.NonNull;

public class Categoria {
    private int id;
    private String nombre;

    public Categoria() {
    }

    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    /** No permitimos modificar el id desde fuera ya que es de tipo autoincrement */
    private void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Categoria categoria = (Categoria) o;

        return id == categoria.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}