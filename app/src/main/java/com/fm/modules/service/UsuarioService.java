package com.fm.modules.service;

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

public class UsuarioService extends RestTemplateEntity<Usuario> implements Serializable {

    public UsuarioService() {
        super(new Usuario(), Usuario.class, Usuario[].class);
    }

    private static final long serialVersionUID = 1L;

    private final String url = Constantes.URL_USUARIO;

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> lista = getListURL(url);
        return lista;
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        Usuario enti = getOneURL(url, id);
        return enti;
    }

    public Usuario obtenerUsuarioPorBody(Usuario objeto) {
        Usuario enti = getByBodyURL(url, objeto);
        return enti;
    }

    public Usuario crearUsuario(Usuario objeto) {
        Usuario enti = createURL(url, objeto);
        return enti;
    }

    public Usuario actualizarUsuarioPorId(Usuario objeto) {
        Usuario enti = updateURL(url, objeto.getUsuarioId(), objeto);
        return enti;
    }

    public void eliminarUsuarioPorId(Long id) {
        deleteURL(url, id);
    }

    public int signIn(Usuario usuario){
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
                ResponseEntity<RespuestaGenerica> responses = restTemplate.exchange(Constantes.DOMINIO.concat("/usuarioLogin/").concat(usuario.getUsername()).concat("/").concat(usuario.getPassword()), HttpMethod.GET,entity, RespuestaGenerica.class);
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
