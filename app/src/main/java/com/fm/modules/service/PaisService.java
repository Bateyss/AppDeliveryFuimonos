package com.fm.modules.service;

import com.fm.modules.models.Pais;

import java.io.Serializable;
import java.util.List;

public class PaisService extends RestTemplateEntity<Pais> implements Serializable {

    private static final long serialVersionUID = 1L;

    public PaisService() {
        super(new Pais(), Pais.class, Pais[].class);
    }

    private final String url = Constantes.URL_PAISES;

    public List<Pais> obtenerPaises() {
        List<Pais> lista = getListURL(url);
        return lista;
    }

    public Pais obtenerPaisPorId(Long id) {
        Pais enti = getOneURL(url, id);
        return enti;
    }

    public Pais obtenerPaisPorBody(Pais objeto) {
        Pais enti = getByBodyURL(url, objeto);
        return enti;
    }

    public Pais crearPais(Pais objeto) {
        Pais enti = createURL(url, objeto);
        return enti;
    }

    public Pais actualizarPaisPorId(Pais objeto) {
        Pais enti = updateURL(url, objeto.getPaisId(), objeto);
        return enti;
    }

    public void eliminarPaisPorId(Long id) {
        deleteURL(url, id);
    }

}
