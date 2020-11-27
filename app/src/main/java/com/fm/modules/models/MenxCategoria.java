package com.fm.modules.models;

public class MenxCategoria {

    private Long menuxcategoria_id;
    private Menu menu;
    private Categoria categoria;

    public MenxCategoria(Long menuxcategoria_id, Menu menu, Categoria categoria) {
        this.menuxcategoria_id = menuxcategoria_id;
        this.menu = menu;
        this.categoria = categoria;
    }

    public MenxCategoria() {
    }

    public Long getMenuxcategoria_id() {
        return menuxcategoria_id;
    }

    public void setMenuxcategoria_id(Long menuxcategoria_id) {
        this.menuxcategoria_id = menuxcategoria_id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{menuxcategoria_id:'");
        builder.append(menuxcategoria_id);
        builder.append("',menus:'");
        builder.append(menu);
        builder.append("',categorias:'");
        builder.append(categoria);
        builder.append("'}");
        return builder.toString();
    }

}
