package com.example.apppet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.ApiService;
import com.example.apppet.R;
import com.example.apppet.RetrofitClient;
import com.example.apppet.UtenteRegisterResponse;
import com.example.apppet.utente.Utente;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrazioneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        EditText nomeUtenteEditText = findViewById(R.id.NomeUtenteEditText);
        EditText passwordUtenteEditText = findViewById(R.id.pwUtenteEditText);
        EditText mailUtenteEditText = findViewById(R.id.mailUtenteEditText);
        EditText cognomeUtenteEditText = findViewById(R.id.cognomeUtenteEditText);
        EditText telefonoUtenteEditText = findViewById(R.id.cellulareUtenteEditText);
        Button registratiBTN = findViewById(R.id.registratiBTN);

        registratiBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeUtente = nomeUtenteEditText.getText().toString();
                String passwordUtente = passwordUtenteEditText.getText().toString();
                String mailUtente = mailUtenteEditText.getText().toString();
                String cognomeUtente = cognomeUtenteEditText.getText().toString();
                String telefonoUtente = telefonoUtenteEditText.getText().toString();
                registerUtente(nomeUtente, passwordUtente, mailUtente, cognomeUtente, telefonoUtente);
            }
        });
    }

    private void registerUtente(String nomeUtente, String passwordUtente, String mailUtente, String cognomeUtente, String telefonoUtente) {
        if (nomeUtente.isEmpty() || passwordUtente.isEmpty() || mailUtente.isEmpty() ||
                cognomeUtente.isEmpty() || telefonoUtente.isEmpty()) {
            Toast.makeText(RegistrazioneActivity.this, "Tutti i campi sono obbligatori", Toast.LENGTH_SHORT).show();
            return;
        }


        Utente ut1 = new Utente(nomeUtente, passwordUtente, mailUtente, cognomeUtente, telefonoUtente);


        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<UtenteRegisterResponse> call = apiService.registrazioneUtente(ut1);

        call.enqueue(new Callback<UtenteRegisterResponse>() {
            @Override
            public void onResponse(Call<UtenteRegisterResponse> call, Response<UtenteRegisterResponse> response) {
                if (response.isSuccessful()) {
                    UtenteRegisterResponse registerResponse = response.body();
                    if (registerResponse != null && "Utente registrato con successo".equals(registerResponse.getMessage())) {
                        Toast.makeText(RegistrazioneActivity.this, "Registrazione effettuata", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistrazioneActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegistrazioneActivity.this, "Errore nella registrazione", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistrazioneActivity.this, "connessione fallita(primo if)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UtenteRegisterResponse> call, Throwable t) {
                Toast.makeText(RegistrazioneActivity.this, "Impossibile connettersi al server", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }
