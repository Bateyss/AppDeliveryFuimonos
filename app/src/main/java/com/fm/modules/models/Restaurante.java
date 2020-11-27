package com.fm.modules.models;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class Restaurante {

    private Long restauranteId;
    private String nombreRestaurante;
    private Departamento departamento;
    private String username;
    private String password;
    private String horarioDeApertura;
    private String horarioDeCierre;
    private String tiempoEstimadoDeEntrega;
    private double descuento;
    private String representante;
    private String numeroDeContacto;
    private double comision;
    private double cargosExtra;
    private Long imagenDePortada;
    private Long logoDeRestaurante;
    private String nit;
    private String correo;
    private boolean destacado;
    private int orden;
    private Boolean disponible;


    public Restaurante() {
    }

    public Restaurante(Long restauranteId, String nombreRestaurante, Departamento departamento, String username, String password, String horarioDeApertura, String horarioDeCierre, String tiempoEstimadoDeEntrega, double descuento, String representante, String numeroDeContacto, double comision, double cargosExtra, Long imagenDePortada, Long logoDeRestaurante, String nit, String correo, boolean destacado, int orden, Boolean disponible) {
        this.restauranteId = restauranteId;
        this.nombreRestaurante = nombreRestaurante;
        this.departamento = departamento;
        this.username = username;
        this.password = password;
        this.horarioDeApertura = horarioDeApertura;
        this.horarioDeCierre = horarioDeCierre;
        this.tiempoEstimadoDeEntrega = tiempoEstimadoDeEntrega;
        this.descuento = descuento;
        this.representante = representante;
        this.numeroDeContacto = numeroDeContacto;
        this.comision = comision;
        this.cargosExtra = cargosExtra;
        this.imagenDePortada = imagenDePortada;
        this.logoDeRestaurante = logoDeRestaurante;
        this.nit = nit;
        this.correo = correo;
        this.destacado = destacado;
        this.orden = orden;
        this.disponible = disponible;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHorarioDeApertura() {
        return horarioDeApertura;
    }

    public void setHorarioDeApertura(String horarioDeApertura) {
        this.horarioDeApertura = horarioDeApertura;
    }

    public String getHorarioDeCierre() {
        return horarioDeCierre;
    }

    public void setHorarioDeCierre(String horarioDeCierre) {
        this.horarioDeCierre = horarioDeCierre;
    }

    public String getTiempoEstimadoDeEntrega() {
        return tiempoEstimadoDeEntrega;
    }

    public void setTiempoEstimadoDeEntrega(String tiempoEstimadoDeEntrega) {
        this.tiempoEstimadoDeEntrega = tiempoEstimadoDeEntrega;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getNumeroDeContacto() {
        return numeroDeContacto;
    }

    public void setNumeroDeContacto(String numeroDeContacto) {
        this.numeroDeContacto = numeroDeContacto;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public double getCargosExtra() {
        return cargosExtra;
    }

    public void setCargosExtra(double cargosExtra) {
        this.cargosExtra = cargosExtra;
    }

    public Long getImagenDePortada() {
        return imagenDePortada;
    }

    public void setImagenDePortada(Long imagenDePortada) {
        this.imagenDePortada = imagenDePortada;
    }

    public Long getLogoDeRestaurante() {
        return logoDeRestaurante;
    }

    public void setLogoDeRestaurante(Long logoDeRestaurante) {
        this.logoDeRestaurante = logoDeRestaurante;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean getDestacado() {
        return destacado;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isDestacado() {
        return destacado;
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
        builder.append("{restauranteId:'");
        builder.append(restauranteId);
        builder.append("',nombreRestaurante");
        builder.append(nombreRestaurante);
        builder.append("',departamentos:'");
        builder.append(departamento);
        builder.append("',username:'");
        builder.append(username);
        builder.append("',password:'");
        builder.append(password);
        builder.append("',horarioDeApertura:'");
        builder.append(horarioDeApertura);
        builder.append("',horarioDeCierre:'");
        builder.append(horarioDeCierre);
        builder.append("',tiempoEstimadoDeEntrega:'");
        builder.append(tiempoEstimadoDeEntrega);
        builder.append("',descuento:'");
        builder.append(descuento);
        builder.append("',representante:'");
        builder.append(representante);
        builder.append("',numeroDeContacto:'");
        builder.append(numeroDeContacto);
        builder.append("',comision:'");
        builder.append(comision);
        builder.append("',cargosExtra:'");
        builder.append(cargosExtra);
        builder.append("',imagenDePortada:'");
        builder.append(imagenDePortada);
        builder.append("',logoDeRestaurante:'");
        builder.append(logoDeRestaurante);
        builder.append("',nit:'");
        builder.append(nit);
        builder.append("',correo:'");
        builder.append(correo);
        builder.append("',disponible:'");
        builder.append(disponible);
        builder.append("'}");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return restauranteId.equals(that.restauranteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restauranteId);
    }
}
