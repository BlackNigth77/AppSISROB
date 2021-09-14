package com.pigra.appsisrob.entidades;

public class Noticia {

    private int id;
    private String titulo;
    private String fecha;
    private String detalle;

    public Noticia(){}

    public Noticia ( String titulo, String fecha, String detalle) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.detalle = detalle;
    }

    public Noticia (int id, String titulo, String fecha, String detalle ) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.detalle = detalle;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
