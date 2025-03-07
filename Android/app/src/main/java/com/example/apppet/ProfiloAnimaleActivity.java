package com.example.apppet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfiloAnimaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //findviewbyid
        setContentView(R.layout.activity_profilo_animale);
        TextView nomeAnimale = findViewById(R.id.nomeProfiloAnimale);
        TextView sessoAnimale = findViewById(R.id.sessoProfiloAnimale);
        TextView altezzaAnimale = findViewById(R.id.altezzaProfiloAnimale);
        TextView pesoAnimale = findViewById(R.id.pesoProfiloAnimale);
        TextView noteAnimale = findViewById(R.id.noteProfiloAnimale);
        Button modificaProfiloAnimale = findViewById(R.id.modificaBTN);
        Button cancellaprofiloAnimale = findViewById(R.id.cancellaBTN);

        //logica database?
        modificaProfiloAnimale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ProfiloAnimaleActivity.this, RegistrazioneAnimaleActivity.class);

            }
        });




    }
}