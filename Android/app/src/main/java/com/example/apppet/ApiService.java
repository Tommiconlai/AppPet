package com.example.apppet;

import com.example.apppet.animale.Animale;
import com.example.apppet.cartellaClinica.LogCartellaClinica;
import com.example.apppet.utente.LoginRequest;
import com.example.apppet.utente.Utente;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/registrazioneUtente")
    Call<RegisterResponse> registrazioneUtente (@Body Utente utente);

    @POST("/registrazioneAnimale")
    Call<RegisterResponse> registrazioneAnimale (@Body Animale animale);

    @POST("login")
    Call<RegisterResponse> login (@Body LoginRequest loginRequest);

    @GET("/listaAnimali")
    Call<ArrayList<Animale>> listaAnimali (@Query("idutente") int idutente);

    //da provare
    @GET("/animale")
    Call<Animale> getAnimale (@Query("idAnimale") int idAnimale);

    @GET("/listaCartellecliniche")
    Call<ArrayList<LogCartellaClinica>> listaCartelleCliniche (@Query("idAnimale") int idAnimale);

    @GET("/catella_clinica")
    Call<ArrayList<LogCartellaClinica>> getAllClinicLogs();

    @POST("/catella_clinica")
    Call<LogCartellaClinica> createClinicLog(@Body LogCartellaClinica log);
    
}
