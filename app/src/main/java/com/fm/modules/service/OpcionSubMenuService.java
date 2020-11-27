package com.fm.modules.service;




import com.fm.modules.models.OpcionesDeSubMenu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OpcionSubMenuService extends RestTemplateEntity<OpcionesDeSubMenu> implements Serializable {

    public OpcionSubMenuService() {
        super(new OpcionesDeSubMenu(), OpcionesDeSubMenu.class, OpcionesDeSubMenu[].class);
    }

    private static final long serialVersionUID = 1L;

    private final String url = Constantes.URL_OPCIONESDESUBMENU;

    public List<OpcionesDeSubMenu> obtenerOpcionSubMenus() {
        List<OpcionesDeSubMenu> lista = getListURL(url);
        return lista;
    }

    public OpcionesDeSubMenu obtenerOpcionSubMenuPorId(Long id) {
        OpcionesDeSubMenu enti = getOneURL(url, id);
        return enti;
    }

    public OpcionesDeSubMenu obtenerOpcionSubMenuPorBody(OpcionesDeSubMenu objeto) {
        OpcionesDeSubMenu enti = getByBodyURL(url, objeto);
        return enti;
    }

    public OpcionesDeSubMenu crearOpcionSubMenu(OpcionesDeSubMenu objeto) {
        OpcionesDeSubMenu enti = createURL(url, objeto);
        return enti;
    }

    public OpcionesDeSubMenu actualizarOpcionSubMenuPorId(OpcionesDeSubMenu objeto) {
        OpcionesDeSubMenu enti = updateURL(url, objeto.getOpcionesDeSubmenuId(), objeto);
        return enti;
    }

    public void eliminarOpcionSubMenuPorId(Long id) {
        deleteURL(url, id);
    }

}
