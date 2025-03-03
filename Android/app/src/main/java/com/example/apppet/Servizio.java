package com.example.apppet;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class Servizio {

    int id_key;

    String nome_attivita;

    String descrizione;

    String fornitore;

    String indirizzo;

    int numerocivico;

    Date orario;

    public Servizio(int id_key, String nome_attivita, String descrizione, String fornitore, String indirizzo, int numerocivico, Date orario) {
        this.id_key = id_key;
        this.nome_attivita = nome_attivita;
        this.descrizione = descrizione;
        this.fornitore = fornitore;
        this.indirizzo = indirizzo;
        this.numerocivico = numerocivico;
        this.orario = orario;
    }
}
