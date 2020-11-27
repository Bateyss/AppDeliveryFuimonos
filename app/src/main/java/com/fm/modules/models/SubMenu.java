package com.fm.modules.models;

public class SubMenu {
    
    private Long subMenuId;
    private Platillo platillo;
    private String titulo;
    private boolean menuCobrado;
    private int cobrarAPartirDe;
    private int maximoOpcionesAEscoger;
    private int minimoOpcionesAEscoger;

    public SubMenu() {
    }

    public SubMenu(Long subMenuId, Platillo platillo, String titulo, boolean menuCobrado, int cobrarAPartirDe,
                   int maximoOpcionesAEscoger, int minimoOpcionesAEscoger) {
        this.subMenuId = subMenuId;
        this.platillo = platillo;
        this.titulo = titulo;
        this.menuCobrado = menuCobrado;
        this.cobrarAPartirDe = cobrarAPartirDe;
        this.maximoOpcionesAEscoger = maximoOpcionesAEscoger;
        this.minimoOpcionesAEscoger = minimoOpcionesAEscoger;
    }

    public Long getSubMenuId() {
        return subMenuId;
    }

    public void setSubMenuId(Long subMenuId) {
        this.subMenuId = subMenuId;
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isMenuCobrado() {
        return menuCobrado;
    }

    public void setMenuCobrado(boolean menuCobrado) {
        this.menuCobrado = menuCobrado;
    }

    public int getCobrarAPartirDe() {
        return cobrarAPartirDe;
    }

    public void setCobrarAPartirDe(int cobrarAPartirDe) {
        this.cobrarAPartirDe = cobrarAPartirDe;
    }

    public int getMaximoOpcionesAEscoger() {
        return maximoOpcionesAEscoger;
    }

    public void setMaximoOpcionesAEscoger(int maximoOpcionesAEscoger) {
        this.maximoOpcionesAEscoger = maximoOpcionesAEscoger;
    }

    public int getMinimoOpcionesAEscoger() {
        return minimoOpcionesAEscoger;
    }

    public void setMinimoOpcionesAEscoger(int minimoOpcionesAEscoger) {
        this.minimoOpcionesAEscoger = minimoOpcionesAEscoger;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{subMenuId:'");
        builder.append(subMenuId);
        builder.append("',platillos:'");
        builder.append(platillo);
        builder.append("',titulo:'");
        builder.append(titulo);
        builder.append("',menuCobrado:'");
        builder.append(menuCobrado);
        builder.append("',cobrarAPartirDe:'");
        builder.append(cobrarAPartirDe);
        builder.append("',maximoOpcionesAEscoger:'");
        builder.append(maximoOpcionesAEscoger);
        builder.append("',minimoOpcionesAEscoger:'");
        builder.append(minimoOpcionesAEscoger);
        builder.append("'}");
        return builder.toString();
    }

}
