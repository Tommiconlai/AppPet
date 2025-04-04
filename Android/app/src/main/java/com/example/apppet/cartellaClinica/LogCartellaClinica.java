package com.example.apppet.cartellaClinica;

public class LogCartellaClinica {
    long id;
    private String title;
    private String date;
    private String description;

    private long idAnimale;

    public LogCartellaClinica(String title, String date, long idAnimale) {

        this.title = title;
        this.date = date;
        this.idAnimale = idAnimale;
    }

    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getDescription() { return description; }

    public long getIdAnimale() {
        return idAnimale;
    }
}

