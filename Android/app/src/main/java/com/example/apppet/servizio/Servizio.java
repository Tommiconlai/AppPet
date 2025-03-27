package com.example.apppet.servizio;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Servizio implements Parcelable {

    //Definisco gli attributi da visualizzare in servizio

    long id;

    String nome_attivita;

    String descrizione;

    String fornitore;

    String indirizzo;

    String cap;

    String orario;

    //Creo il costruttore di SERVIZIO

    public Servizio(int id,String nome_attivita, String descrizione, String fornitore, String indirizzo, String numerocivico, String orario) {

        this.id=id;
        this.nome_attivita = nome_attivita;
        this.descrizione = descrizione;
        this.fornitore = fornitore;
        this.indirizzo = indirizzo;
        this.cap = numerocivico;
        this.orario = orario;

    }

    //Svolgo l'operazione di ritorno degli attributi della classe


    protected Servizio(Parcel in) {
        id = in.readLong();
        nome_attivita = in.readString();
        descrizione = in.readString();
        fornitore = in.readString();
        indirizzo = in.readString();
        cap = in.readString();
        orario = in.readString();
    }

    public static final Creator<Servizio> CREATOR = new Creator<Servizio>() {
        @Override
        public Servizio createFromParcel(Parcel in) {
            return new Servizio(in);
        }

        @Override
        public Servizio[] newArray(int size) {
            return new Servizio[size];
        }
    };

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

    public String getCap() {
        return cap;
    }

    public String getOrario() {
        return orario;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nome_attivita);
        dest.writeString(descrizione);
        dest.writeString(fornitore);
        dest.writeString(indirizzo);
        dest.writeString(cap);
        dest.writeString(orario);
    }
}
