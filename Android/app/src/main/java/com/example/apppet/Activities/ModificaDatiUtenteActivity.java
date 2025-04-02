package com.example.apppet.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apppet.R;

public class ModificaDatiUtenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_dati_utente);

        Button button = findViewById(R.id.modificaButton);
        button.setOnClickListener(v -> {

            EditText nomeET = findViewById(R.id.nomeET);
            EditText cognomeET = findViewById(R.id.cognomeET);
            EditText telefonoET = findViewById(R.id.telefonoET);
            EditText mailET = findViewById(R.id.mailET);

            String nome = nomeET.getText().toString();
            String cognome = cognomeET.getText().toString();
            String telefono = telefonoET.getText().toString();
            String mail = mailET.getText().toString();

            // logica per inviare i dati modificati al server
            // ...
            finish();


        });

    }
}