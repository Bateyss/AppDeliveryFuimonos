package com.fm.modules.service;

import com.fm.modules.models.PlatilloSeleccionado;

import java.io.Serializable;
import java.util.List;

public class PlatilloSeleccionadoService extends RestTemplateEntity<PlatilloSeleccionado> implements Serializable {

    public PlatilloSeleccionadoService() {
        super(new PlatilloSeleccionado(), PlatilloSeleccionado.class, PlatilloSeleccionado[].class);
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    private final String url = Constantes.URL_PLATILLOSELC;

    public List<PlatilloSeleccionado> obtenerPlatilloSeleccionados() {
        List<PlatilloSeleccionado> lista = getListURL(url);
        return lista;
    }

    public PlatilloSeleccionado obtenerPlatilloSeleccionadoPorId(Long id) {
        PlatilloSeleccionado enti = getOneURL(url, id);
        return enti;
    }

    public PlatilloSeleccionado obtenerPlatilloSeleccionadoPorBody(PlatilloSeleccionado objeto) {
        PlatilloSeleccionado enti = getByBodyURL(url, objeto);
        return enti;
    }

    public PlatilloSeleccionado crearPlatilloSeleccionado(PlatilloSeleccionado objeto) {
        PlatilloSeleccionado enti = createURL(url, objeto);
        return enti;
    }

    public PlatilloSeleccionado actualizarPlatilloSeleccionadoPorId(PlatilloSeleccionado objeto) {
        PlatilloSeleccionado enti = updateURL(url, objeto.getPlatilloSeleccionadoId(), objeto);
        return enti;
    }

    public void eliminarPlatilloSeleccionadoPorId(Long id) {
        deleteURL(url, id);
    }

}
