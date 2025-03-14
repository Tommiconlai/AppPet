package com.example.apppet.cartellaClinica;

public class LogCartellaClinica {
    private String title;
    private String date;
    private String description;

    public LogCartellaClinica(String title, String date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getDescription() { return description; }
}

