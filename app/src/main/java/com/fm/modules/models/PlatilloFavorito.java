package com.fm.modules.models;

public class PlatilloFavorito {


    private Long platilloFavoritoId;
    private Platillo platillo;
    private Usuario usuarios;

    public PlatilloFavorito() {
    }

    public PlatilloFavorito(Long platilloFavoritoId, Platillo platillos, Usuario usuario) {
        this.platilloFavoritoId = platilloFavoritoId;
        this.platillo = platillos;
        this.usuarios = usuario;
    }

    public Long getPlatilloFavoritoId() {
        return platilloFavoritoId;
    }

    public void setPlatilloFavoritoId(Long platilloFavoritoId) {
        this.platilloFavoritoId = platilloFavoritoId;
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public void setPlatillo(Platillo platillos) {
        this.platillo = platillos;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuario) {
        this.usuarios = usuario;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{platilloFavoritoId:'");
        builder.append(platilloFavoritoId);
        builder.append("',platillos:'");
        builder.append(platillo);
        builder.append("',usuarios:'");
        builder.append(usuarios);
        builder.append("'}");
        return builder.toString();
    }

}
