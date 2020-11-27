package com.fm.modules.service;

import com.fm.modules.entities.RespuestaPedidosDriver;
import com.fm.modules.models.Pedido;
import com.fm.modules.models.SubMenu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PedidoService extends RestTemplateEntity<Pedido> implements Serializable {

    private static final long serialVersionUID = 1L;

    public PedidoService() {
        super(new Pedido(), Pedido.class, Pedido[].class);
    }

    private final String url = Constantes.URL_PEDIDO;

    public List<Pedido> obtenerPedidos() {
        List<Pedido> lista = getListURL(url);
        return lista;
    }

    public Pedido obtenerPedidoPorId(Long id) {
        Pedido enti = getOneURL(url, id);
        return enti;
    }

    public Pedido obtenerPedidoPorBody(Pedido objeto) {
        Pedido enti = getByBodyURL(url, objeto);
        return enti;
    }

    public Pedido crearPedido(Pedido objeto) {
        Pedido enti = createURL(url, objeto);
        return enti;
    }

    public Pedido actualizarPedidoPorId(Pedido objeto) {
        Pedido enti = updateURL(url, objeto.getPedidoId(), objeto);
        return enti;
    }

    public void eliminarPedidoPorId(Long id) {
        deleteURL(url, id);
    }


    public List<RespuestaPedidosDriver> obtenerPedidoDriver(String idDriver) {
        List<RespuestaPedidosDriver> list = new LinkedList<>();
        try {
            RestTemplate restTemplat = new RestTemplate();
            ResponseEntity<RespuestaPedidosDriver[]> response = restTemplat.getForEntity(Constantes.DOMINIO.concat("/").concat("obtenerPedidoDriver/").concat(idDriver), RespuestaPedidosDriver[].class);

            list = Arrays.asList(response.getBody());

        } catch (Exception e) {
            System.out.println("error absRest getListURL: " + e.getMessage() +" " +e.getClass());
        }
        return list;
    }

    public List<RespuestaPedidosDriver> obtenerPedidoAEntregar(String idDriver) {
        List<RespuestaPedidosDriver> list = new LinkedList<>();
        try {
            RestTemplate restTemplat = new RestTemplate();
            ResponseEntity<RespuestaPedidosDriver[]> response = restTemplat.getForEntity(Constantes.DOMINIO.concat("/").concat("obtenerPedidoAEntregar/").concat(idDriver), RespuestaPedidosDriver[].class);

            list = Arrays.asList(response.getBody());

        } catch (Exception e) {
            System.out.println("error absRest getListURL: " + e.getMessage() +" " +e.getClass());
        }
        return list;
    }

    public List<Pedido> historialPedidoDriver(String idDriver) {
        List<Pedido> list = new LinkedList<>();
        try {
            RestTemplate restTemplat = new RestTemplate();
            ResponseEntity<Pedido[]> response = restTemplat.getForEntity(Constantes.DOMINIO.concat("/").concat("historialDriver/").concat(idDriver), Pedido[].class);
            list = Arrays.asList(response.getBody());
            System.out.println("Contenido Historial Driver: " +list.size());
            System.out.println("Lista: " +list.get(0).toString());
        } catch (Exception e) {
            System.out.println("error absRest HistorialDriver getListURL: " + e.getMessage() +" " +e.getClass());
        }
        return list;
    }

    public int pedidosByDriverCount(Long idRestaurante) {
        int i = 0;
        try {
            RestTemplate restTemplat = new RestTemplate();
            ResponseEntity<Integer> response = restTemplat.getForEntity(Constantes.DOMINIO.concat("/").concat("push/getpedidosdc/").concat(idRestaurante.toString()), Integer.class);
            i = response.getBody();
        } catch (Exception e) {
            System.out.println("error  pedidosByRestaurantCount getListURL: " + e.getMessage() + " " + e.getClass());
        }
        return i;
    }
}
