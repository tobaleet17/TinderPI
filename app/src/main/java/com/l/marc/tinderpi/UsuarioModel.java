package com.l.marc.tinderpi;

public class UsuarioModel {

    String uid;
    String urlImagen;

    public UsuarioModel(String uid, String urlImagen) {
        this.uid = uid;
        this.urlImagen = urlImagen;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
