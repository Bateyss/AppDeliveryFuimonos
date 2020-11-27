package com.fm.modules.service;


import com.fm.modules.models.Administrador;

import java.io.Serializable;
import java.util.List;

public class AdministradorService extends RestTemplateEntity<Administrador> implements Serializable {

    public AdministradorService() {
        super(new Administrador(), Administrador.class, Administrador[].class);
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    private final String url = Constantes.URL_ADMINISTRADOR;

    public List<Administrador> obtenerAdministradors() {
        List<Administrador> lista = getListURL(url);
        return lista;
    }

    public Administrador obtenerAdministradorPorId(Long id) {
        Administrador enti = getOneURL(url, id);
        return enti;
    }

    public Administrador obtenerAdministradorPorBody(Administrador objeto) {
        Administrador enti = getByBodyURL(url, objeto);
        return enti;
    }

    public Administrador crearAdministrador(Administrador objeto) {
        Administrador enti = createURL(url, objeto);
        return enti;
    }

    public Administrador actualizarAdministradorPorId(Administrador objeto) {
        Administrador enti = updateURL(url, objeto.getAdministradorId(), objeto);
        return enti;
    }

    public void eliminarAdministradorPorId(Long id) {
        deleteURL(url, id);
    }

}
