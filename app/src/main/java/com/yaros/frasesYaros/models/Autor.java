package com.yaros.frasesYaros.models;

public class Autor {
    private int id;
    private String nombre;
    private int nacimiento;
    private int muerte;
    private String profesion;

    public Autor(int id, String nombre, int nacimiento, int muerte, String profesion) {
        this.id = id;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.muerte = muerte;
        this.profesion = profesion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNacimiento() {
        return nacimiento;
    }

    public int getMuerte() {
        return muerte;
    }

    public String getProfesion() {
        return profesion;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", muerte=" + muerte +
                ", profesion='" + profesion + '\'' +
                '}';
    }
}
