package com.example.apppet.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.R;

public class ProfiloUtenteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_utente);

        ImageButton BTNmodificaDati = (ImageButton) findViewById(R.id.BTN_mod_dati_utente);
        BTNmodificaDati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
