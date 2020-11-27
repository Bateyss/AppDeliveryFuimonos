package com.fm.modules.service;

import com.fm.modules.models.Restaurante;

import java.io.Serializable;
import java.util.List;

public class RestauranteService extends RestTemplateEntity<Restaurante> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RestauranteService() {
        super(new Restaurante(), Restaurante.class, Restaurante[].class);
    }

    private final String url = Constantes.URL_RESTAURANTES;

    public List<Restaurante> obtenerRestaurantes() {
        List<Restaurante> lista = getListURL(url);
        return lista;
    }

    public Restaurante obtenerRestaurantePorId(Long id) {
        Restaurante enti = getOneURL(url, id);
        return enti;
    }

    public Restaurante obtenerRestaurantePorBody(Restaurante objeto) {
        Restaurante enti = getByBodyURL(url, objeto);
        return enti;
    }

    public Restaurante crearRestaurante(Restaurante objeto) {
        Restaurante enti = createURL(url, objeto);
        return enti;
    }

    public Restaurante actualizarRestaurantePorId(Restaurante objeto) {
        Restaurante enti = updateURL(url, objeto.getRestauranteId(), objeto);
        return enti;
    }

    public void eliminarRestaurantePorId(Long id) {
        deleteURL(url, id);
    }
}
