package com.fm.modules.models;

public class Municipio {

    private Long municipioId;
    private Departamento departamento;
    private String nombreMunicipio;

    public Municipio() {
    }

    public Municipio(Long municipioId, Departamento departamento, String nombreMunicipio) {
        this.municipioId = municipioId;
        this.departamento = departamento;
        this.nombreMunicipio = nombreMunicipio;
    }

    public Long getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

}
