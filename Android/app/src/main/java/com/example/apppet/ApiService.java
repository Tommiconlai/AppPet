package com.example.apppet;

import com.example.apppet.animale.Animale;
import com.example.apppet.cartellaClinica.LogCartellaClinica;
import com.example.apppet.utente.LoginRequest;
import com.example.apppet.utente.Utente;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/registrazioneUtente")
    Call<RegisterResponse> registrazioneUtente (@Body Utente utente);

    @POST("/registrazioneAnimale")
    Call<RegisterResponse> registrazioneAnimale (@Body Animale animale);

    @POST("login")
    Call<RegisterResponse> login (@Body LoginRequest loginRequest);

    @GET("/listaAnimali")
    Call<ArrayList<Animale>> listaAnimali (@Query("idutente") long idutente);

    //da provare
    @GET("/animale")
    Call<Animale> getAnimale (@Query("idAnimale") int idAnimale);

    @GET("/listaCartelleCliniche")
    Call<ArrayList<LogCartellaClinica>> listaCartelleCliniche (@Query("idAnimale") long idAnimale);

    @PUT("/modificaAnimale")
    Call<Animale> modificaAnimale (@Body Animale animale);

    @PUT("/modificaAnimale/rating/{id}")
    Call<Animale> modificaRating (@Path ("id")long idAnimale, @Body Animale animale);


    @POST("/salvaCartellaClinica")
    Call<RegisterResponse> salvaCartellaClinica (@Body LogCartellaClinica logCartellaClinica);

    @POST("/modificaUtente")
    Call<Utente> modificaUtente (@Body Utente utente);

    @GET("/cercaUtente")
    Call<Utente> cercaUtente (@Query("idutente") long idutente);

    @DELETE("/rimuoviUtente")
    Call<RegisterResponse> rimuoviUtente (@Query("idutente") long idutente);

    @DELETE("/rimuoviCartellaClinica")
    Call<RegisterResponse> rimuoviCartellaClinica (@Query("idCartella") long id);

    @DELETE("/rimuoviAnimale")
    Call<RegisterResponse> rimuoviAnimale (@Query("idAnimale") long id);
}
