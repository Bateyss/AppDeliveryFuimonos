package com.fm.modules.service;

import com.fm.modules.models.Driver;
import com.fm.modules.models.RespuestaGenerica;
import com.fm.modules.models.Usuario;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;

public class DriverService extends RestTemplateEntity<Driver> implements Serializable {

    public DriverService() {
        super(new Driver(), Driver.class, Driver[].class);
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    private final String url = Constantes.URL_DRIVER;

    public List<Driver> obtenerDrivers() {
        List<Driver> lista = getListURL(url);
        return lista;
    }

    public Driver obtenerDriverPorId(Long id) {
        Driver enti = getOneURL(url, id);
        return enti;
    }

    public Driver obtenerDriverPorBody(Driver objeto) {
        Driver enti = getByBodyURL(url, objeto);
        return enti;
    }

    public Driver crearDriver(Driver objeto) {
        Driver enti = createURL(url, objeto);
        return enti;
    }

    public Driver actualizarDriverPorId(Driver objeto) {
        Driver enti = updateURL(url, objeto.getDriverId(), objeto);
        return enti;
    }

    public void eliminarDriverPorId(Long id) {
        deleteURL(url, id);
    }

    public int signIn(Driver driver){
        int i = 0;
        /*
         * 0 = server problem
         * -1 = bad username
         * -2 = bad password
         * x = signed in
         * -4 = inactive
         * */
        try {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
                    @Override
                    protected boolean hasError(HttpStatus statusCode) {
                        // desactivar el error loader
                        // al recivir una respuesta de tipo error
                        return false;
                    }
                });
                HttpEntity<Object> entity = new HttpEntity<Object>(headers);
                ResponseEntity<RespuestaGenerica> responses = restTemplate.exchange(Constantes.DOMINIO.concat("/driverLogin/").concat(driver.getUsername()).concat("/").concat(driver.getPassword()), HttpMethod.GET,entity, RespuestaGenerica.class);
                RespuestaGenerica response = responses.getBody();
                if (response != null){
                    if ("1".equals(response.getCodigo())){}
                    switch (response.getCodigo()){
                        case "1": i = -1;break;
                        case "2": i = -2;break;
                        case "3": i = Integer.parseInt(response.getMensaje());break;
                        case "4": i = -4;break;
                    }
                }
            } catch (Exception e) {
                System.out.println("error absRest s: " + e);

            }
        }catch (Exception e){}
        return i;
    }
}
