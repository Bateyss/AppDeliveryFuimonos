package com.fm.modules.models;

public class RestauranteDestacado {


    private Long restauranteDestacadoId;
    private Restaurante restaurante;
    private String nombre;
    private Long imagen;
    private double montoMinimo;
    private Long logo;
    private int orden;

    public RestauranteDestacado() {
    }

    public RestauranteDestacado(Long restauranteDestacadoId, Restaurante restaurante, String nombre, Long imagen,
                                double montoMinimo, Long logo, int orden) {
        this.restauranteDestacadoId = restauranteDestacadoId;
        this.restaurante = restaurante;
        this.nombre = nombre;
        this.imagen = imagen;
        this.montoMinimo = montoMinimo;
        this.logo = logo;
        this.orden = orden;
    }

    public Long getRestauranteDestacadoId() {
        return restauranteDestacadoId;
    }

    public void setRestauranteDestacadoId(Long restauranteDestacadoId) {
        this.restauranteDestacadoId = restauranteDestacadoId;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurantes(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getImagen() {
        return imagen;
    }

    public void setImagen(Long imagen) {
        this.imagen = imagen;
    }

    public double getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(double montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public Long getLogo() {
        return logo;
    }

    public void setLogo(Long logo) {
        this.logo = logo;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{restauranteDestacadoId:'");
        builder.append(restauranteDestacadoId);
        builder.append("',restaurantes:'");
        builder.append(restaurante);
        builder.append("',nombre:'");
        builder.append(nombre);
        builder.append("',montoMinimo:'");
        builder.append(montoMinimo);
        builder.append("',orden:'");
        builder.append(orden);
        builder.append("'}");
        return builder.toString();
    }

}
