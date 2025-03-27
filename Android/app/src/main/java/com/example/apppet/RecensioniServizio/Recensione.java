package com.example.apppet.RecensioniServizio;

public class Recensione {
    long id;
    long ID_prenotazione;
    String nomeAutore;
    float value;

    public Recensione(String nomeAutore, float value) {
        this.nomeAutore = nomeAutore;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getID_prenotazione() {
        return ID_prenotazione;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getNomeAutore() {
        return nomeAutore;
    }

    public void setNomeAutore(String nomeAutore) {
        this.nomeAutore = nomeAutore;
    }

}
