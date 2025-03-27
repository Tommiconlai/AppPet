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

    @GET("/catella_clinica")
    Call<List<LogCartellaClinica>> getAllClinicLogs();

    @POST("/catella_clinica")
    Call<LogCartellaClinica> createClinicLog(@Body LogCartellaClinica log);
    
}
