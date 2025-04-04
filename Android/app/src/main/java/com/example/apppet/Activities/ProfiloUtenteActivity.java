package com.example.apppet.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.ApiService;
import com.example.apppet.R;
import com.example.apppet.RegisterResponse;
import com.example.apppet.RetrofitClient;
import com.example.apppet.utente.Utente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ProfiloUtenteActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    long idutente;

    TextView tvNomeUtente, tvCognomeUtente, tvEmailUtente, tvTelefonoUtente, tvPasswordUtente;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_utente);

        sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);
        idutente = sharedPreferences.getLong("userId", 0);

        System.out.println("Id utente = " + idutente);

        tvNomeUtente = findViewById(R.id.nome_utente);
        tvCognomeUtente = findViewById(R.id.cognome_utente);
        tvEmailUtente = findViewById(R.id.email_utente);
        tvTelefonoUtente = findViewById(R.id.telefono_utente);
        tvPasswordUtente = findViewById(R.id.password_utente);

        inizializzaUtente();

        TextView cancellaProfilo = findViewById(R.id.btn_rimuovi_utente);
        cancellaProfilo.setOnClickListener(v ->{
            Intent intent = new Intent(ProfiloUtenteActivity.this, LoginActivity.class);
            cancellaProfilo();
            startActivity(intent);
        });

        ImageButton BTNmodificaDati = findViewById(R.id.BTN_mod_dati_utente);
        BTNmodificaDati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfiloUtenteActivity.this, ModificaDatiUtenteActivity.class);
                startActivity(intent);

            }
        });
    }

    public void inizializzaUtente(){
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Utente> call = apiService.cercaUtente(idutente);
        call.enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<Utente> call, Response<Utente> response) {
                System.out.println("Codice risposta = " + response.code());

                if (response.isSuccessful()) {
                    Utente utente = response.body();
                    if (utente != null) {
                        tvNomeUtente.setText(utente.getNome());
                        tvCognomeUtente.setText(utente.getCognome());
                        tvEmailUtente.setText(utente.getEmail());
                        tvTelefonoUtente.setText(utente.getTelefono());
                        tvPasswordUtente.setText(utente.getPassword());
                    } else {
                        Toast.makeText(ProfiloUtenteActivity.this, "Utente non trovato", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(ProfiloUtenteActivity.this, "Errore durante la ricerca dell'utente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Utente> call, Throwable t) {
                Toast.makeText(ProfiloUtenteActivity.this, "Impossibile connettersi al server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cancellaProfilo(){
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<RegisterResponse> call = apiService.rimuoviUtente(idutente);

        call.enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse deletedLog = response.body();
                    if (deletedLog != null && "Utente cancellata".equals(response.body().getMessage())) {
                        Toast.makeText(ProfiloUtenteActivity.this, "Utente cancellato con successo", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfiloUtenteActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(ProfiloUtenteActivity.this, "Errore nell'eliminazione della cartella clinica", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ProfiloUtenteActivity.this, "connessione fallita(primo if)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(ProfiloUtenteActivity.this, "Impossibile connettersi al server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
