package com.example.apppet;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class Servizio {

    //Definisco gli attributi da visualizzare in servizio

    String nome_attivita;

    String descrizione;

    String fornitore;

    String indirizzo;

    int numerocivico;

    Date orario;

    //Creo il costruttore di SERVIZIO

    public Servizio( String nome_attivita, String descrizione, String fornitore, String indirizzo, int numerocivico, Date orario) {

        this.nome_attivita = nome_attivita;
        this.descrizione = descrizione;
        this.fornitore = fornitore;
        this.indirizzo = indirizzo;
        this.numerocivico = numerocivico;
        this.orario = orario;

    }

    //Svolgo l'operazione di ritorno degli attributi della classe


    public String getNome_attivita() {
        return nome_attivita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getFornitore() {
        return fornitore;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public int getNumerocivico() {
        return numerocivico;
    }

    public Date getOrario() {
        return orario;

    }
}
