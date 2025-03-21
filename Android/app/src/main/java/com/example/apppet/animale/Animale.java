package com.example.apppet.animale;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Animale implements Parcelable {
    String nome;
    String peso;
    String altezza;
    String note;
    String sesso;
    float ratingAnimale;

    public Animale(String nome, float rating, String peso, String altezza, String note, String sesso) {
        super();
        this.nome = nome;
        this.ratingAnimale = rating;
        this.peso = peso;
        this.altezza = altezza;
        this.note = note;
        this.sesso = sesso;
    }

    protected Animale(Parcel in) {
        nome = in.readString();
        peso = in.readString();
        altezza = in.readString();
        note = in.readString();
        sesso = in.readString();
        ratingAnimale = in.readFloat();
    }

    public static final Creator<Animale> CREATOR = new Creator<Animale>() {
        @Override
        public Animale createFromParcel(Parcel in) {
            return new Animale(in);
        }

        @Override
        public Animale[] newArray(int size) {
            return new Animale[size];
        }
    };

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setAltezza(String altezza) {
        this.altezza = altezza;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public void setRatingAnimale(float ratingAnimale) {
        this.ratingAnimale = ratingAnimale;
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

    public String isSesso() {
        return sesso;
    }

    public float getRatingAnimale() {
        return ratingAnimale;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(peso);
        dest.writeString(altezza);
        dest.writeString(note);
        dest.writeString(sesso);
        dest.writeFloat(ratingAnimale);
    }
}
