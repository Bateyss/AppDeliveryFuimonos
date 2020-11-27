package com.fm.modules.service;


import com.fm.modules.models.Menu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MenuService extends RestTemplateEntity<Menu> implements Serializable {

    private static final long serialVersionUID = 1L;

    public MenuService() {
        super(new Menu(), Menu.class, Menu[].class);
    }

    private final String url = Constantes.URL_MENU;

    public List<Menu> obtenerMenus() {
        List<Menu> lista = getListURL(url);
        return lista;
    }

    public Menu obtenerMenuPorId(Long id) {
        Menu enti = getOneURL(url, id);
        return enti;
    }

    public Menu obtenerMenuPorBody(Menu objeto) {
        Menu enti = getByBodyURL(url, objeto);
        return enti;
    }

    public Menu crearMenu(Menu objeto) {
        Menu enti = createURL(url, objeto);
        return enti;
    }

    public Menu actualizarMenuPorId(Menu objeto) {
        Menu enti = updateURL(url, objeto.getMenuId(), objeto);
        return enti;
    }

    public void eliminarMenuPorId(Long id) {
        deleteURL(url, id);
    }

}
