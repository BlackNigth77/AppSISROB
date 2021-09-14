package com.pigra.appsisrob.entidades;


import java.util.Date;

public class ReporteFalla {

    private int id;
    private String fecha;
    private String unidad_minera;
    private String equipo;
    private String tipo;
    private String sistema;
    private String observacion;
    private String foto;
    private String ubicacion;

    public ReporteFalla(int anInt, String string, String cursorString, String tipo, String sistema, String observacion, String foto, String ubicacion){}

    public ReporteFalla (String fecha, String unidad_minera, String equipo, String tipo,String sistema,String observacion,String foto ,String ubicacion  ) {
        this.fecha = fecha;
        this.unidad_minera = unidad_minera;
        this.equipo = equipo;
        this.tipo = tipo;
        this.sistema = sistema;
        this.observacion = observacion;
        this.foto = foto;
        this.ubicacion = ubicacion;
    }

    public ReporteFalla (int id, String fecha, String unidad_minera, String equipo, String tipo,String sistema,String observacion,String foto ,String ubicacion  ) {
        this.id = id;
        this.fecha = fecha;
        this.unidad_minera = unidad_minera;
        this.equipo = equipo;
        this.tipo = tipo;
        this.sistema = sistema;
        this.observacion = observacion;
        this.foto = foto;
        this.ubicacion = ubicacion;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUnidad_minera() {
        return unidad_minera;
    }

    public void setUnidad_minera(String unidad_minera) {
        this.unidad_minera = unidad_minera;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
