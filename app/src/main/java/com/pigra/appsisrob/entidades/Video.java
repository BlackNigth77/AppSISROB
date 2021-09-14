package com.pigra.appsisrob.entidades;

public class Video {

    private int id;
    private String titulo;
    private int duracion;
    private String ruta;

    public Video(){}

    public Video (String titulo, int duracion, String ruta) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.ruta = ruta;
    }

    public Video(int id, String titulo, int duracion, String ruta ) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.ruta = ruta;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
