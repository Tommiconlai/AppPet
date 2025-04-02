package com.example.apppet.Activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apppet.R;
import com.example.apppet.utente.Utente;

public class ModificaDatiUtenteActivity extends AppCompatActivity {

    Utente u1 = new Utente("", "", "", "", "");

    SharedPreferences sharedPreferences;

    long idUtente;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_dati_utente);

        SharedPreferences sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);
        idUtente = sharedPreferences.getLong("userId", 0);


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

            u1.setNome(nome);
            u1.setCognome(cognome);
            u1.setTelefono(telefono);
            u1.setEmail(mail);



            // logica per inviare i dati modificati al server
            finish();


        });

    }
}