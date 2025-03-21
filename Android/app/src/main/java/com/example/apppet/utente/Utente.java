package com.example.apppet.utente;

import com.google.gson.annotations.SerializedName;

public class Utente {
    long id;
    @SerializedName("nome")
    String nome;
    @SerializedName("cognome")
    String cognome;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
    @SerializedName("telefono")
    String telefono;



    public Utente(String nome, String cognome, String email, String password, String telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

