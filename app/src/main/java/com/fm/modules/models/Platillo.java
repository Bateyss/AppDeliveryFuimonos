package com.fm.modules.models;

import java.util.Arrays;

public class Platillo {

    private Long platilloId;
    private Menu menu;
    private String nombre;
    private double precioBase;
    private Long imagen;
    private String descripcion;
    private int orden;
    private Boolean disponible;

    public Platillo() {
    }

    public Platillo(Long platilloId, Menu menu, String nombre, double precioBase, Long imagen, String descripcion,
                    int orden, Boolean disponible) {
        this.platilloId = platilloId;
        this.menu = menu;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.orden = orden;
        this.disponible = disponible;
    }

    public Long getPlatilloId() {
        return platilloId;
    }

    public void setPlatilloId(Long platilloId) {
        this.platilloId = platilloId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public Long getImagen() {
        return imagen;
    }

    public void setImagen(Long imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{platilloId:'");
        builder.append(platilloId);
        builder.append("',menus:'");
        builder.append(menu);
        builder.append("',nombre:'");
        builder.append(nombre);
        builder.append("',precioBase:'");
        builder.append(precioBase);
        builder.append("',imagen:'");
        builder.append(imagen);
        builder.append("',descripcion:'");
        builder.append(descripcion);
        builder.append("',orden:'");
        builder.append(orden);
        builder.append("',disponible:'");
        builder.append(disponible);
        builder.append("'}");
        return builder.toString();
    }

}
