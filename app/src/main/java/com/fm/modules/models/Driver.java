package com.fm.modules.models;

public class Driver {

    private Long driverId;
    private String username;
    private String password;
    private String nombreDriver;
    private Boolean habilitado;
    private String horaDeEntrada;
    private String horaDeSalida;
    private String fechaCreado;
    private Boolean statusAsignado;

    public Driver() {
    }

    public Driver(Long driverId, String username, String password, String nombreDriver, Boolean habilitado,
                  String horaDeEntrada, String horaDeSalida, String fechaCreado, Boolean statusAsignado) {
        this.driverId = driverId;
        this.username = username;
        this.password = password;
        this.nombreDriver = nombreDriver;
        this.habilitado = habilitado;
        this.horaDeEntrada = horaDeEntrada;
        this.horaDeSalida = horaDeSalida;
        this.fechaCreado = fechaCreado;
        this.statusAsignado = statusAsignado;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
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

    public String getNombreDriver() {
        return nombreDriver;
    }

    public void setNombreDriver(String nombreDriver) {
        this.nombreDriver = nombreDriver;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getHoraDeEntrada() {
        return horaDeEntrada;
    }

    public void setHoraDeEntrada(String horaDeEntrada) {
        this.horaDeEntrada = horaDeEntrada;
    }

    public String getHoraDeSalida() {
        return horaDeSalida;
    }

    public void setHoraDeSalida(String horaDeSalida) {
        this.horaDeSalida = horaDeSalida;
    }

    public String getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(String fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public Boolean getStatusAsignado() {
        return statusAsignado;
    }

    public void setStatusAsignado(Boolean statusAsignado) {
        this.statusAsignado = statusAsignado;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{driverId:'");
        builder.append(driverId);
        builder.append("',username:'");
        builder.append(username);
        builder.append("',password:'");
        builder.append(password);
        builder.append("',nombreDriver:'");
        builder.append(nombreDriver);
        builder.append("',habilitado:'");
        builder.append(habilitado);
        builder.append("',horaDeEntrada:'");
        builder.append(horaDeEntrada);
        builder.append("',horaDeSalida:'");
        builder.append(horaDeSalida);
        builder.append("',fechaCreado:'");
        builder.append(fechaCreado);
        builder.append("',statusAsignado:'");
        builder.append(statusAsignado);
        builder.append("'}");
        return builder.toString();
    }

}
