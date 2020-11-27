package com.fm.modules.models;

public class OpcionesDeSubMenuSeleccionado {

    private Long opcionesDeSubMenuSeleccionadoId;
    private PlatilloSeleccionado platilloSeleccionado;
    private OpcionesDeSubMenu opcionesDeSubMenu;
    private String nombre;

    public OpcionesDeSubMenuSeleccionado() {
    }

    public OpcionesDeSubMenuSeleccionado(Long opcionesDeSubMenuSeleccionadoId,
                                         PlatilloSeleccionado platilloSeleccionado, OpcionesDeSubMenu opcionesDeSubMenu, String nombre) {
        this.opcionesDeSubMenuSeleccionadoId = opcionesDeSubMenuSeleccionadoId;
        this.platilloSeleccionado = platilloSeleccionado;
        this.opcionesDeSubMenu = opcionesDeSubMenu;
        this.nombre = nombre;
    }

    public Long getOpcionesDeSubMenuSeleccionadoId() {
        return opcionesDeSubMenuSeleccionadoId;
    }

    public void setOpcionesDeSubMenuSeleccionadoId(Long opcionesDeSubMenuSeleccionadoId) {
        this.opcionesDeSubMenuSeleccionadoId = opcionesDeSubMenuSeleccionadoId;
    }

    public PlatilloSeleccionado getPlatilloSeleccionado() {
        return platilloSeleccionado;
    }

    public void setPlatilloSeleccionado(PlatilloSeleccionado platilloSeleccionado) {
        this.platilloSeleccionado = platilloSeleccionado;
    }

    public OpcionesDeSubMenu getOpcionesDeSubMenu() {
        return opcionesDeSubMenu;
    }

    public void setOpcionesDeSubMenu(OpcionesDeSubMenu opcionesDeSubMenu) {
        this.opcionesDeSubMenu = opcionesDeSubMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{opcionesDeSubMenuSeleccionadoId:'");
        builder.append(opcionesDeSubMenuSeleccionadoId);
        builder.append("',platilloSeleccionado:'");
        builder.append(platilloSeleccionado);
        builder.append("',opcionesDeSubMenu:'");
        builder.append(opcionesDeSubMenu);
        builder.append("',nombre:'");
        builder.append(nombre);
        builder.append("'}");
        return builder.toString();
    }

}
