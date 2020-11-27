package com.fm.modules.service;

import com.fm.modules.models.Image;

import java.io.Serializable;
import java.util.List;

public class ImageService extends RestTemplateEntity<Image> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ImageService() {
        super(new Image(), Image.class, Image[].class);
    }

    private final String url = Constantes.URL_IMAGES;

    public List<Image> obtenerImagenes() {
        List<Image> lista = getListURL(url);
        return lista;
    }

    public Image obtenerImagenPorId(Long id) {
        Image enti = getOneURL(url, id);
        return enti;
    }

    public Image obtenerImagenPorBody(Image objeto) {
        Image enti = getByBodyURL(url, objeto);
        return enti;
    }

    public Image crearImagen(Image objeto) {
        Image enti = createURL(url, objeto);
        return enti;
    }

    public Image actualizarImagenPorId(Image objeto) {
        Image enti = updateURL(url, objeto.getId(), objeto);
        return enti;
    }

    public void eliminarImagenPorId(Long id) {
        deleteURL(url, id);
    }
}
