package com.fm.modules.service;

import com.fm.modules.models.OpcionesDeSubMenu;

import java.io.Serializable;
import java.util.List;

public class OpcionesDeSubMenuService extends RestTemplateEntity<OpcionesDeSubMenu> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public OpcionesDeSubMenuService() {
        super(new OpcionesDeSubMenu(), OpcionesDeSubMenu.class, OpcionesDeSubMenu[].class);
    }

    private final String url = Constantes.URL_OPCIONESDESUBMENUSELC;

    public List<OpcionesDeSubMenu> obtenerOpcionesDeSubMenu() {
        List<OpcionesDeSubMenu> lista = getListURL(url);
        return lista;
    }

    public OpcionesDeSubMenu obtenerOpcionesDeSubMenuPorId(Long id) {
        OpcionesDeSubMenu enti = getOneURL(url, id);
        return enti;
    }

    public OpcionesDeSubMenu obtenerOpcionesDeSubMenuPorBody(OpcionesDeSubMenu objeto) {
        OpcionesDeSubMenu enti = getByBodyURL(url, objeto);
        return enti;
    }

    public OpcionesDeSubMenu crearOpcionesDeSubMenuPorId(OpcionesDeSubMenu objeto) {
        OpcionesDeSubMenu enti = createURL(url, objeto);
        return enti;
    }

    public OpcionesDeSubMenu actializarOpcionesDeSubMenuPorId(OpcionesDeSubMenu objeto) {
        OpcionesDeSubMenu enti = updateURL(url, objeto.getOpcionesDeSubmenuId(), objeto);
        return enti;
    }

    public void eliminarOpcionesDeSubMenuPorId(Long id) {
        deleteURL(url, id);
    }
}
