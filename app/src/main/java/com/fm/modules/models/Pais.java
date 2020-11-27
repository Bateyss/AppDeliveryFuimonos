package com.fm.modules.models;

public class Pais {

    private Long paisId;
	private String nombrePais;

	public Pais() {
	}

	public Pais(Long paisId, String nombrePais) {
		this.paisId = paisId;
		this.nombrePais = nombrePais;
	}

    public Long getPaisId() {
        return paisId;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{paisId:'");
        builder.append(paisId);
        builder.append("',nombrePais:'");
        builder.append(nombrePais);
        builder.append("'}");
        return builder.toString();
    }


}
