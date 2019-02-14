package com.l.marc.tinderpi;

public class ChatPickerModel {

    private String nombreUser;
    private String id;
    private String imagen;

    public ChatPickerModel() {

    }

    public ChatPickerModel(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public ChatPickerModel(String nombreUser, String id) {
        this.nombreUser = nombreUser;
        this.id = id;
    }

    public ChatPickerModel(String nombreUser, String id, String imagen) {
        this.nombreUser = nombreUser;
        this.id = id;
        this.imagen=imagen;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String ultimMensaje) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
