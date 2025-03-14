package com.example.apppet.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.R;

public class RegistrazioneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        EditText nomeUtente=findViewById(R.id.NomeUtenteEditText);
        EditText passwordUtente=findViewById(R.id.pwUtenteEditText);
        EditText mailUtente=findViewById(R.id.mailUtenteEditText);
        EditText cognomeUtente=findViewById(R.id.cognomeUtenteEditText);
        EditText telefonoUtente=findViewById(R.id.cellulareUtenteEditText);
        Button registratiBTN = findViewById(R.id.registratiBTN);


    }
}