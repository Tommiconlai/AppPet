package com.example.apppet.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.R;
import com.example.apppet.utente.Utente;

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



    }
}