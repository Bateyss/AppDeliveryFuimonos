package com.fm.modules.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RespuestaPedidosDriver {
    private long pedidoId;
    private String restaurante;
    private long restauranteId;
    private String usuario;
    private long usuarioId;
    private String driver;
    private long driverId;
    private int status;
    private String formaDePago;
    private double totalDePedido;
    private double totalEnRestautante;
    private double totalDeCargosExtra;
    private double totalEnRestautanteSinComision;
    private boolean pedidoPagado;
    private String fechaOrdenado;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date tiempoPromedioEntrega;
    private boolean pedidoEntregado;
    private String notas;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date tiempoAdicional;
    private String direccion;


    public RespuestaPedidosDriver() {
        super();
    }

    public RespuestaPedidosDriver(long pedidoId, String restaurante, long restauranteId, String usuario, long usuarioId,
                                  String driver, long driverId, int status, String formaDePago, double totalDePedido,
                                  double totalEnRestautante, double totalDeCargosExtra, double totalEnRestautanteSinComision,
                                  boolean pedidoPagado, String fechaOrdenado, Date tiempoPromedioEntrega, boolean pedidoEntregado, String notas,
                                  Date tiempoAdicional, String direccion) {
        super();
        this.pedidoId = pedidoId;
        this.restaurante = restaurante;
        this.restauranteId = restauranteId;
        this.usuario = usuario;
        this.usuarioId = usuarioId;
        this.driver = driver;
        this.driverId = driverId;
        this.status = status;
        this.formaDePago = formaDePago;
        this.totalDePedido = totalDePedido;
        this.totalEnRestautante = totalEnRestautante;
        this.totalDeCargosExtra = totalDeCargosExtra;
        this.totalEnRestautanteSinComision = totalEnRestautanteSinComision;
        this.pedidoPagado = pedidoPagado;
        this.fechaOrdenado = fechaOrdenado;
        this.tiempoPromedioEntrega = tiempoPromedioEntrega;
        this.pedidoEntregado = pedidoEntregado;
        this.notas = notas;
        this.tiempoAdicional = tiempoAdicional;
        this.direccion = direccion;
    }

    public long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public double getTotalDePedido() {
        return totalDePedido;
    }

    public void setTotalDePedido(double totalDePedido) {
        this.totalDePedido = totalDePedido;
    }

    public double getTotalEnRestautante() {
        return totalEnRestautante;
    }

    public void setTotalEnRestautante(double totalEnRestautante) {
        this.totalEnRestautante = totalEnRestautante;
    }

    public double getTotalDeCargosExtra() {
        return totalDeCargosExtra;
    }

    public void setTotalDeCargosExtra(double totalDeCargosExtra) {
        this.totalDeCargosExtra = totalDeCargosExtra;
    }

    public double getTotalEnRestautanteSinComision() {
        return totalEnRestautanteSinComision;
    }

    public void setTotalEnRestautanteSinComision(double totalEnRestautanteSinComision) {
        this.totalEnRestautanteSinComision = totalEnRestautanteSinComision;
    }

    public boolean isPedidoPagado() {
        return pedidoPagado;
    }

    public void setPedidoPagado(boolean pedidoPagado) {
        this.pedidoPagado = pedidoPagado;
    }

    public String getFechaOrdenado() {
        return fechaOrdenado;
    }

    public void setFechaOrdenado(String fechaOrdenado) {
        this.fechaOrdenado = fechaOrdenado;
    }

    public Date getTiempoPromedioEntrega() {
        return tiempoPromedioEntrega;
    }

    public void setTiempoPromedioEntrega(Date tiempoPromedioEntrega) {
        this.tiempoPromedioEntrega = tiempoPromedioEntrega;
    }

    public boolean isPedidoEntregado() {
        return pedidoEntregado;
    }

    public void setPedidoEntregado(boolean pedidoEntregado) {
        this.pedidoEntregado = pedidoEntregado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Date getTiempoAdicional() {
        return tiempoAdicional;
    }

    public void setTiempoAdicional(Date tiempoAdicional) {
        this.tiempoAdicional = tiempoAdicional;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "RespuestaPedidosDriver [pedidoId=" + pedidoId + ", restaurante=" + restaurante + ", restauranteId="
                + restauranteId + ", usuario=" + usuario + ", usuarioId=" + usuarioId + ", driver=" + driver
                + ", driverId=" + driverId + ", status=" + status + ", formaDePago=" + formaDePago + ", totalDePedido="
                + totalDePedido + ", totalEnRestautante=" + totalEnRestautante + ", totalDeCargosExtra="
                + totalDeCargosExtra + ", totalEnRestautanteSinComision=" + totalEnRestautanteSinComision
                + ", pedidoPagado=" + pedidoPagado + ", fechaOrdenado=" + fechaOrdenado + ", tiempoPromedioEntrega="
                + tiempoPromedioEntrega + ", pedidoEntregado=" + pedidoEntregado + ", notas=" + notas
                + ", tiempoAdicional=" + tiempoAdicional + ", direccion=" + direccion + "]";
    }

}
