package com.pigra.appsisrob.entidades;

public class Equipo {

    private int id;
    private String descripcion;
    private String tipo;
    private String marca;
    private String modelo;
    private int capacidad;

    public Equipo() {}

    public Equipo(String descripcion, String tipo, String marca, String modelo, int capacidad) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
    }

    public Equipo(int id, String descripcion, String tipo, String marca, String modelo, int capacidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
