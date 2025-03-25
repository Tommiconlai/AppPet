package com.example.apppet;

import com.example.apppet.animale.Animale;
import com.example.apppet.utente.LoginRequest;
import com.example.apppet.utente.Utente;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/registrazioneUtente")
    Call<RegisterResponse> registrazioneUtente (@Body Utente utente);

    @POST("/registrazioneAnimale")
    Call<RegisterResponse> registrazioneAnimale (@Body Animale animale);

    @POST("login")
    Call<RegisterResponse> login (@Body LoginRequest loginRequest);
}
