package com.example.apppet;

import com.example.apppet.utente.Utente;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("registrazione")
    Call<ResponseBody> registrazioneUtente(@Body Utente utente);
}
