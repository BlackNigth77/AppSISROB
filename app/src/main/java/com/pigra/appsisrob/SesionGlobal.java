package com.pigra.appsisrob;

import android.app.Application;

import com.pigra.appsisrob.entidades.Usuario;
import com.pigra.appsisrob.entidades.UsuarioOpcion;

import java.util.List;

public class SesionGlobal extends Application {
    private Usuario usuario;
    private List<UsuarioOpcion> opciones;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<UsuarioOpcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<UsuarioOpcion> opciones) {
        this.opciones = opciones;
    }
}
