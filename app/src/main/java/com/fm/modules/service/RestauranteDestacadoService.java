package com.fm.modules.service;

import com.fm.modules.models.RestauranteDestacado;

import java.io.Serializable;
import java.util.List;

public class RestauranteDestacadoService extends RestTemplateEntity<RestauranteDestacado> implements Serializable {

    public RestauranteDestacadoService() {
        super(new RestauranteDestacado(), RestauranteDestacado.class, RestauranteDestacado[].class);
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    private final String url = Constantes.URL_RESTAURANTES;

    public List<RestauranteDestacado> obtenerRestauranteDestacados() {
        List<RestauranteDestacado> lista = getListURL(url);
        return lista;
    }

    public RestauranteDestacado obtenerRestauranteDestacadoPorId(Long id) {
        RestauranteDestacado enti = getOneURL(url, id);
        return enti;
    }

    public RestauranteDestacado obtenerRestauranteDestacadoPorBody(RestauranteDestacado objeto) {
        RestauranteDestacado enti = getByBodyURL(url, objeto);
        return enti;
    }

    public RestauranteDestacado crearRestauranteDestacado(RestauranteDestacado objeto) {
        RestauranteDestacado enti = createURL(url, objeto);
        return enti;
    }

    public RestauranteDestacado actualizarRestauranteDestacadoPorId(RestauranteDestacado objeto) {
        RestauranteDestacado enti = updateURL(url, objeto.getRestauranteDestacadoId(), objeto);
        return enti;
    }

    public void eliminarRestauranteDestacadoPorId(Long id) {
        deleteURL(url, id);
    }

}
