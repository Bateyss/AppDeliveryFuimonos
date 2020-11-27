package com.fm.modules.service;

import com.fm.modules.models.MenxCategoria;

import java.io.Serializable;
import java.util.List;

public class MenuxCategoriaService extends RestTemplateEntity<MenxCategoria> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MenuxCategoriaService() {
        super(new MenxCategoria(), MenxCategoria.class, MenxCategoria[].class);
    }

    private final String url = Constantes.URL_MENUXCATEGORIA;

    public List<MenxCategoria> obtenerMenuxCateogoria() {
        List<MenxCategoria> lista = getListURL(url);
        return lista;
    }

    public MenxCategoria obtenerMenuxCateogoriaPorId(Long id) {
        MenxCategoria enti = getOneURL(url, id);
        return enti;
    }

    public MenxCategoria obtenerMenuxCateogoriaPorBody(MenxCategoria objeto) {
        MenxCategoria enti = getByBodyURL(url, objeto);
        return enti;
    }

    public MenxCategoria crearMenuxCateogoriaPorId(MenxCategoria objeto) {
        MenxCategoria enti = createURL(url, objeto);
        return enti;
    }

    public MenxCategoria actializarMenuxCateogoria(MenxCategoria objeto) {
        MenxCategoria enti = updateURL(url, objeto.getMenuxcategoria_id(), objeto);
        return enti;
    }

    public void eliminarMenuxCateogoriaPorId(Long id) {
        deleteURL(url, id);
    }

    public List<MenxCategoria> obtenerRestaurantesxCateg() {
        List<MenxCategoria> lista = getListURL(url.concat("/categoria"));
        return lista;
    }
}
