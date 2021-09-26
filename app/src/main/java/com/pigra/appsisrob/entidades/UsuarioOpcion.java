package com.pigra.appsisrob.entidades;

public class UsuarioOpcion {
    private int DNI;
    private int IdOpcion;
    private String Visible;

    public UsuarioOpcion() {

    }

    public UsuarioOpcion(int DNI, int idOpcion, String visible) {
        this.DNI = DNI;
        IdOpcion = idOpcion;
        Visible = visible;
    }

    public String getVisible() {
        return Visible;
    }

    public void setVisible(String visible) {
        Visible = visible;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public int getIdOpcion() {
        return IdOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        IdOpcion = idOpcion;
    }
}
