package com.fm.modules.models;

public class Departamento {

    private Long departamentoId;
    private Pais pais;
    private String nombreDepartamento;

    public Departamento() {
    }

    public Departamento(Long departamentoId, Pais pais, String nombreDepartamento) {
        this.departamentoId = departamentoId;
        this.pais = pais;
        this.nombreDepartamento = nombreDepartamento;
    }

    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{departamentoId:'");
        builder.append(departamentoId);
        builder.append("',paises:");
        builder.append(pais);
        builder.append(",nombrePais:'");
        builder.append(nombreDepartamento);
        builder.append("'}");
        return builder.toString();
    }

}
