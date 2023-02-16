package com.yaros.frasesYaros.models;

import androidx.annotation.NonNull;

public class Autor {
    private int id;
    private String nombre;
    /** Año de nacimiento del autor. Números negativos representan AC (Antes de Cristo) */
    private int nacimiento;
    /** Año de muerte del autor. Números negativos representan AC (Antes de Cristo) */
    private String muerte;
    private String profesion;

    public Autor() {
    }

    public Autor(int id, String nombre, int nacimiento, String muerte, String profesion) {
        this.id = id;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.muerte = muerte;
        this.profesion = profesion;
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

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getMuerte() {
        return muerte;
    }

    public void setMuerte(String muerte) {
        if(muerte != null && muerte.length() == 0) {
            this.muerte = null;
        } else {
            this.muerte = muerte;
        }
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Autor autor = (Autor) o;
        return id == autor.id;
    }

    @Override
    public int hashCode() {
        //return Objects.hash(id);
        return id;
    }

    /*@NonNull
    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", muerte='" + muerte + '\'' +
                ", profesion='" + profesion + '\'' +
                '}';
    }*/

    @NonNull
    @Override
    public String toString() {
        return nombre;
    }
    public String all() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", muerte='" + muerte + '\'' +
                ", profesion='" + profesion + '\'' +
                '}';
    }

}
