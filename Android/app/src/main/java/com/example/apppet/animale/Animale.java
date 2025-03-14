package com.example.apppet.animale;

public class Animale  {
    String nome;
    String peso;
    String altezza;
    String note;
    String sesso;
    String dataNascita;
    float ratingAnimale;

    public Animale(String nome, float rating, String peso, String altezza, String note, String sesso, String dataNascita) {
        super();
        this.nome = nome;
        this.ratingAnimale = rating;
        this.peso = peso;
        this.altezza = altezza;
        this.note = note;
        this.sesso = sesso;
        this.dataNascita = dataNascita;
    }

    public String getNome() {
        return nome;
    }

    public String getPeso() {
        return peso;
    }

    public String getAltezza() {
        return altezza;
    }

    public String getNote() {
        return note;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public String isSesso() {
        return sesso;
    }

    public float getRatingAnimale() {
        return ratingAnimale;
    }
}
