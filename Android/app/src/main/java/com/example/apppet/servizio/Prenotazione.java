package com.example.apppet.servizio;

import java.util.Date;

public class Prenotazione {

    long id_animale;
    long id_prenotazione;
    String nome_attivita;

    String tipo_attivita;

    Date orario;

    public Prenotazione(long id_animale, long id_prenotazione, String nome_attivita, String tipo_attivita, Date orario) {
        this.id_animale = id_animale;
        this.id_prenotazione = id_prenotazione;
        this.nome_attivita = nome_attivita;
        this.tipo_attivita = tipo_attivita;
        this.orario = orario;
    }

    public long getId_animale() {
        return id_animale;
    }

    public void setId_animale(long id_animale) {
        this.id_animale = id_animale;
    }

    public long getId_prenotazione() {
        return id_prenotazione;
    }

    public void setId_prenotazione(long id_prenotazione) {
        this.id_prenotazione = id_prenotazione;
    }

    public String getNome_attivita() {
        return nome_attivita;
    }

    public void setNome_attivita(String nome_attivita) {
        this.nome_attivita = nome_attivita;
    }

    public String getTipo_attivita() {
        return tipo_attivita;
    }

    public void setTipo_attivita(String tipo_attivita) {
        this.tipo_attivita = tipo_attivita;
    }

    public Date getOrario() {
        return orario;
    }

    public void setOrario(Date orario) {
        this.orario = orario;
    }
}
