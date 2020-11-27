package com.fm.modules.service;

import com.fm.modules.models.PlatilloFavorito;

import java.io.Serializable;
import java.util.List;

public class PlatilloFavoritoService extends RestTemplateEntity<PlatilloFavorito> implements Serializable {

    private static final long serialVersionUID = 1L;

    public PlatilloFavoritoService() {
        super(new PlatilloFavorito(), PlatilloFavorito.class, PlatilloFavorito[].class);
    }

    private final String url = Constantes.URL_PLATILLOFAV;

    public List<PlatilloFavorito> obtenerPlatilloFavoritos() {
        List<PlatilloFavorito> lista = getListURL(url);
        return lista;
    }

    public PlatilloFavorito obtenerPlatilloFavoritoPorId(Long id) {
        PlatilloFavorito enti = getOneURL(url, id);
        return enti;
    }

    public PlatilloFavorito obtenerPlatilloFavoritoPorBody(PlatilloFavorito objeto) {
        PlatilloFavorito enti = getByBodyURL(url, objeto);
        return enti;
    }

    public PlatilloFavorito crearPlatilloFavorito(PlatilloFavorito objeto) {
        PlatilloFavorito enti = createURL(url, objeto);
        return enti;
    }

    public PlatilloFavorito actualizarPlatilloFavoritoPorId(PlatilloFavorito objeto) {
        PlatilloFavorito enti = updateURL(url, objeto.getPlatilloFavoritoId(), objeto);
        return enti;
    }

    public void eliminarPlatilloFavoritoPorId(Long id) {
        deleteURL(url, id);
    }

}
