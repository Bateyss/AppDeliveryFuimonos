package com.fm.modules.models;

public class RespuestaGenerica {
	
	private String codigo;
	private String mensaje;
	private String descripcion;
	
	public RespuestaGenerica() {
	}

	public RespuestaGenerica(String codigo, String mensaje, String descripcion) {
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "RespuestaGenerica [codigo=" + codigo + ", mensaje=" + mensaje + ", descripcion=" + descripcion + "]";
	}
	
	

}
