package com.fm.modules.service;

import com.fm.modules.models.Municipio;

import java.io.Serializable;
import java.util.List;

public class MunicipioService extends RestTemplateEntity<Municipio> implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MunicipioService() {
        super(new Municipio(), Municipio.class, Municipio[].class);
    }

    private final String url = Constantes.URL_MUNICIPIO;

    public List<Municipio> obtenerMunicipios() {
        List<Municipio> lista = getListURL(url);
        return lista;
    }

    public Municipio obtenerMunicipiosPorId(Long id) {
        Municipio enti = getOneURL(url, id);
        return enti;
    }

    public Municipio obtenerMunicipiosporBody(Municipio objeto) {
        Municipio enti = getByBodyURL(url, objeto);
        return enti;
    }

    public Municipio crearMunicipios(Municipio objeto) {
        Municipio enti = createURL(url, objeto);
        return enti;
    }

    public Municipio actualizarMunicipio(Municipio objeto) {
        Municipio enti = updateURL(url, objeto.getMunicipioId(), objeto);
        return enti;
    }

    public void eliminarMunicipio(Long id) {
        deleteURL(url, id);
    }
}
