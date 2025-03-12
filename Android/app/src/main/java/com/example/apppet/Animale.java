package com.example.apppet;

import android.widget.RatingBar;

public class Animale  {
    String nome;
    float peso;
    float altezza;
    String note;
    boolean sesso;
    String dataNascita;
    float ratingAnimale;

    public Animale(String nome, float peso, float altezza, String note, boolean sesso, String dataNascita) {
        super();
        this.nome = nome;
        this.peso = peso;
        this.altezza = altezza;
        this.note = note;
        this.sesso = sesso;
        this.dataNascita = dataNascita;
    }

    public Animale(String nome, float rating){
        this.nome = nome;
        this.ratingAnimale = rating;
    }

    public String getNome() {
        return nome;
    }

    public float getPeso() {
        return peso;
    }

    public float getAltezza() {
        return altezza;
    }

    public String getNote() {
        return note;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public boolean isSesso() {
        return sesso;
    }

    public float getRatingAnimale() {
        return ratingAnimale;
    }
}
