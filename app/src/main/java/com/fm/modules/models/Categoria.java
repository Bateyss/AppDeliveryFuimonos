package com.fm.modules.models;

public class Categoria {

    private Long categoriaId;
    private String nombreCategoria;

    public Categoria() {
    }

    public Categoria(Long categoriaId, String nombreCategoria) {
        this.categoriaId = categoriaId;
        this.nombreCategoria = nombreCategoria;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{categoriaId:'");
        builder.append(categoriaId);
        builder.append("',nombreCategoria:'");
        builder.append(nombreCategoria);
        builder.append("'}");
        return builder.toString();
    }

}
