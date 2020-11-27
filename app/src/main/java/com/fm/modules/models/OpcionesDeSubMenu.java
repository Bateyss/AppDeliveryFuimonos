package com.fm.modules.models;

public class OpcionesDeSubMenu {

    private Long opcionesDeSubmenuId;
    private SubMenu subMenu;
    private String nombre;
    private double precio;
    private int orden;
    private boolean seleccionadoPorDefecto;

    public OpcionesDeSubMenu() {
    }

    public OpcionesDeSubMenu(Long opcionesDeSubmenuId, SubMenu subMenu, String nombre, double precio, int orden,
                             boolean seleccionadoPorDefecto) {
        this.opcionesDeSubmenuId = opcionesDeSubmenuId;
        this.subMenu = subMenu;
        this.nombre = nombre;
        this.precio = precio;
        this.orden = orden;
        this.seleccionadoPorDefecto = seleccionadoPorDefecto;
    }

    public Long getOpcionesDeSubmenuId() {
        return opcionesDeSubmenuId;
    }

    public void setOpcionesDeSubmenuId(Long opcionesDeSubmenuId) {
        this.opcionesDeSubmenuId = opcionesDeSubmenuId;
    }

    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isSeleccionadoPorDefecto() {
        return seleccionadoPorDefecto;
    }

    public void setSeleccionadoPorDefecto(boolean seleccionadoPorDefecto) {
        this.seleccionadoPorDefecto = seleccionadoPorDefecto;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{opcionesDeSubmenuId:'");
        builder.append(opcionesDeSubmenuId);
        builder.append("',subMenu:'");
        builder.append(subMenu);
        builder.append("',nombre:'");
        builder.append(nombre);
        builder.append("',precio:'");
        builder.append(precio);
        builder.append("',orden:'");
        builder.append(orden);
        builder.append("',seleccionadoPorDefecto:'");
        builder.append(seleccionadoPorDefecto);
        builder.append("'}");
        return builder.toString();
    }

}
