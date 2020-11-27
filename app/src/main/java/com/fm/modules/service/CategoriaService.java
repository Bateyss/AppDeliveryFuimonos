package com.fm.modules.service;

import com.fm.modules.models.Categoria;

import java.io.Serializable;
import java.util.List;

public class CategoriaService extends RestTemplateEntity<Categoria> implements Serializable {

    public CategoriaService() {
        super(new Categoria(), Categoria.class, Categoria[].class);
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;


    private final String url = Constantes.URL_CATEGORIA;

    public List<Categoria> obtenerCategorias() {
        List<Categoria> lista = getListURL(url);
        return lista;
    }

    public Categoria obtenerCategoriaPorId(Long id) {
        Categoria enti = getOneURL(url, id);
        return enti;
    }

    public Categoria obtenerCategoriaPorBody(Categoria objeto) {
        Categoria enti = getByBodyURL(url, objeto);
        return enti;
    }

    public Categoria crearCategoria(Categoria objeto) {
        Categoria enti = createURL(url, objeto);
        return enti;
    }

    public Categoria actualizarCategoriaPorId(Categoria objeto) {
        Categoria enti = updateURL(url, objeto.getCategoriaId(), objeto);
        return enti;
    }

    public void eliminarCategoriaPorId(Long id) {
        deleteURL(url, id);
    }
}
