package com.yaros.frasesYaros.models;


import androidx.annotation.NonNull;

public class Frase {
    private static final String TAG = Class.class.getSimpleName();

    private int id;
    private String texto;
    private String fechaProgramada;
    private Autor autor;
    private Categoria categoria;

    public Frase() {
    }

    public Frase(String texto, String fechaProgramada, Autor autor, Categoria categoria) {
        this.texto = texto;
        this.fechaProgramada = fechaProgramada;
        this.autor = autor;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    /**
     * No permitimos modificar el id desde fuera ya que es de tipo autoincrement
     */
    private void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFechaProgramada() {
        return this.fechaProgramada;
    }

    /*
    public void setFechaProgramada(String fecha) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(fecha);
            if(!fecha.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            Log.e(TAG, ex.getMessage());
        }
        if(date == null) {
            Log.e(TAG, "Formato de fecha incorrecto. Patrón [yyyy-MM-dd]. Fecha = "+fecha);
            this.fechaProgramada = null;
        } else {
            this.fechaProgramada = fecha;
        }
    }
     */

    public Autor getAutor() {
        return this.autor;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Frase frase = (Frase) o;
        return id == frase.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Frase{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", fechaProgramada='" + fechaProgramada + '\'' +
                ", autor=" + autor +
                ", categoria=" + categoria +
                '}';
    }
}
