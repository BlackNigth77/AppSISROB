package com.pigra.appsisrob.entidades;

import android.app.Application;

import java.util.List;

public class SesionGlobal extends Application {
    private Usuario usuario;
    private List<UsuarioOpcion> opciones;

    public List<UsuarioOpcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<UsuarioOpcion> opciones) {
        this.opciones = opciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
