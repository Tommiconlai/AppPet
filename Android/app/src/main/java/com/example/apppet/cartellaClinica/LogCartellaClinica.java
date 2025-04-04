package com.example.apppet.cartellaClinica;

import com.google.gson.annotations.SerializedName;

public class LogCartellaClinica {
    long id;
    @SerializedName("titolo")
    private String title;

    private String date;
    @SerializedName("descrizione")
    private String description;

    private long idAnimale;

    public LogCartellaClinica(String title, String description, long idAnimale) {

        this.title = title;
        this.description = description;
        this.idAnimale = idAnimale;
    }

    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getDescription() { return description; }

    public long getIdAnimale() {
        return idAnimale;
    }
}

