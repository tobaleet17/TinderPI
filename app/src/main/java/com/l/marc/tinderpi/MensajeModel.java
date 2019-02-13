package com.l.marc.tinderpi;

public class MensajeModel {

    String msg;
    UsuarioModel emisor;
    long horaEnvio;

    public MensajeModel(String msg, UsuarioModel emisor, long horaEnvio) {
        this.msg = msg;
        this.emisor = emisor;
        this.horaEnvio = horaEnvio;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UsuarioModel getEmisor() {
        return emisor;
    }

    public void setEmisor(UsuarioModel emisor) {
        this.emisor = emisor;
    }

    public long getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(long horaEnvio) {
        this.horaEnvio = horaEnvio;
    }
}
