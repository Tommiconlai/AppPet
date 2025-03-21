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
import com.example.apppet.utente.Utente;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class RegistrazioneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        String nomeUtente=findViewById(R.id.NomeUtenteEditText).toString();
        String passwordUtente=findViewById(R.id.pwUtenteEditText).toString();
        String mailUtente=findViewById(R.id.mailUtenteEditText).toString();
        String cognomeUtente=findViewById(R.id.cognomeUtenteEditText).toString();
        String telefonoUtente=findViewById(R.id.cellulareUtenteEditText).toString();
        Button registratiBTN = findViewById(R.id.registratiBTN);



        Utente utente = new Utente(nomeUtente,cognomeUtente,mailUtente,passwordUtente,telefonoUtente);

        registratiBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeUt = nomeUtente;
                String passwordUt  = passwordUtente;
                String mailUt = mailUtente;
                String cognomeUt = cognomeUtente;
                String telefonoUt = telefonoUtente;

                if (nomeUt.isEmpty() || passwordUt.isEmpty() || mailUt.isEmpty() ||
                        cognomeUt.isEmpty() || telefonoUt.isEmpty()) {
                    Toast.makeText(RegistrazioneActivity.this, "Completa tutti i campi", Toast.LENGTH_SHORT).show();
                } else{
                    Utente utente = new Utente(nomeUt, cognomeUt, mailUt, passwordUt, telefonoUt);

                }
            }
        });
        

    }
}