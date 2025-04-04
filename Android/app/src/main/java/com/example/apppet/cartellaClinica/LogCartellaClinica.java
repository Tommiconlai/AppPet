package com.example.apppet.cartellaClinica;

import com.google.gson.annotations.SerializedName;

public class LogCartellaClinica {
    @SerializedName("id")
    long id;
    @SerializedName("titolo")
    private String title;

    @SerializedName("descrizione")
    private String description;

    @SerializedName("idAnimale")
    private long idAnimale;

    public LogCartellaClinica(String title, String description, long idAnimale) {

        this.title = title;
        this.description = description;
        this.idAnimale = idAnimale;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getIdAnimale() {
        return idAnimale;
    }
}

