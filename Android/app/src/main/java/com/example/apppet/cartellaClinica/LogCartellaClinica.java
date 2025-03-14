package com.example.apppet.cartellaClinica;

public class LogCartellaClinica {
    long id;
    private String title;
    private String date;
    private String description;

    public LogCartellaClinica(long id,String title, String date, String description) {
        this.id=id;
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getDescription() { return description; }
}

