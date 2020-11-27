package com.fm.modules.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class RestTemplateEntity<Entity> {

    // atributos
    // sirven para evitar los null
    // los atributos con Entity sera iniciados desde el constructos
    // evitando null pointer exceptions
    private final Entity ent;
    private final Class<Entity> classEntity;
    private final Class<Entity[]> classEntityArray;

    private RestTemplate restTemplate;


    /*
     *  el patron del constructor a seguir se basa en inicializar
     *   * * * **  * * * ** * new Entity()  * *  Entity.class * *  * * *   Entity[].class **
     */
    public RestTemplateEntity(Entity newEntity, Class<Entity> classEntity, Class<Entity[]> classEntityArray) {
        this.ent = newEntity;
        this.classEntity = classEntity;
        this.classEntityArray = classEntityArray;
    }


    public List<Entity> getListURL(String url) {
        // metodo GET de rest template, convierte el resultado al Entity generado
        List<Entity> list = new LinkedList<>();
        try {
            restTemplate = new RestTemplate();
            /*
             * Entity[] es la declaracion de una lista
             * classEntityArray es la declaracion del entity array como class (Entity[].class)
             */
            ResponseEntity<Entity[]> response = restTemplate.getForEntity(url, classEntityArray);
            list = Arrays.asList(response.getBody());
        } catch (Exception e) {
            System.out.println("error absRest getListURL: " + e);
        }
        return list;
    }

    public Entity getOneURL(String url, Long id) {
        // metodo GET solo recibe un parametro basado en el id del entity
        Entity entity = ent;
        try {
            /*
             * Entity es la declaracion de una entity
             * classEntity es la declaracion del entity como class (Entity.class)
             */
            restTemplate = new RestTemplate();
            ResponseEntity<Entity> response = restTemplate.getForEntity(url.concat("/").concat(id.toString()), classEntity);
            entity = response.getBody();
        } catch (Exception e) {
            System.out.println("error absRest getListURL: " + e);
            entity = null;
        }
        return entity;
    }

    public Entity createURL(String url, Entity entity) {
        // metodo POST permite enviar datos encriptados
        Entity etity = ent;
        try {
            /*
             * Entity es la declaracion de una entity
             * classEntity es la declaracion del entity como class (Entity.class)
             */
            restTemplate = new RestTemplate();
            HttpEntity<Entity> request = new HttpEntity<>(entity);
            ResponseEntity<Entity> response = restTemplate.exchange(url, HttpMethod.POST, request, classEntity);
            etity = response.getBody();
        } catch (Exception e) {
            System.out.println("error absRest getListURL: " + e.getMessage() + " " +e.getClass() +"\nCausa: C" +e.getCause());
            etity = null;
        }
        return etity;
    }

    public Entity updateURL(String url, Long id, Entity entity) {
        // ** metodo PUT para enviar datos de actualziacion, no encrypta datos de manera segura
        Entity etity = ent;
        try {
            /*
             * Entity es la declaracion de una entity
             * classEntity es la declaracion del entity como class (Entity.class)
             */
            restTemplate = new RestTemplate();
            HttpEntity<Entity> request = new HttpEntity<>(entity);
            ResponseEntity<Entity> response = restTemplate.exchange(url.concat("/").concat(id.toString()), HttpMethod.PUT, request, classEntity);
            etity = response.getBody();
        } catch (Exception e) {
            System.out.println("error absRest getListURL: " + e);
            etity = null;
        }
        return etity;
    }

    public void deleteURL(String url, Long id) {
        // ** DELETE permite la eliminacion de datos, no verifica la respuesta
        try {
            restTemplate = new RestTemplate();
            restTemplate.delete(url.concat("/").concat(id.toString()));
            ;
        } catch (Exception e) {
            System.out.println("error absRest getListURL: " + e);
        }

    }

    public Entity getByBodyURL(String url, Entity entity) {
        // metodo GET permite consultar una entity usando parametros con una misma entity
        Entity etity = ent;
        try {
            /*
             * Entity es la declaracion de una entity
             * classEntity es la declaracion del entity como class (Entity.class)
             */

            restTemplate = new RestTemplate();
            HttpEntity<Entity> request = new HttpEntity<>(entity);
            ResponseEntity<Entity> response = restTemplate.getForEntity(url, classEntity, request);
            etity = response.getBody();
        } catch (Exception e) {
            System.out.println("error absRest getListURL: " + e);
            etity = null;
        }
        return etity;
    }

}
