package com.example.apppet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.R;
import com.example.apppet.animale.Animale;

public class ProfiloAnimaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_animale);

        //Get Extras
        /*
        String nomeAnimale = getIntent().getStringExtra("NOME");
        float ratingAnimale = getIntent().getFloatExtra("RATING", 0);
        String pesoAnimale = getIntent().getStringExtra("PESO");
        String altezzaAnimale = getIntent().getStringExtra("ALTEZZA");
        String noteAnimale = getIntent().getStringExtra("NOTE");
        String sessoAnimale = getIntent().getStringExtra("SESSO");
        String dataNascita = getIntent().getStringExtra("DATANASCITA");

         */
        Animale animale = getIntent().getParcelableExtra("ANIMALE");

        //findviewbyid
        TextView tvNomeAnimale = findViewById(R.id.nomeProfiloAnimale);
        TextView tvSessoAnimale = findViewById(R.id.sessoProfiloAnimale);
        TextView tvAltezzaAnimale = findViewById(R.id.altezzaProfiloAnimale);
        TextView tvPesoAnimale = findViewById(R.id.pesoProfiloAnimale);
        TextView tvNoteAnimale = findViewById(R.id.noteProfiloAnimale);
        TextView tvDataNascita = findViewById(R.id.dataProfiloAnimale);

        //Buttons
        ImageButton modificaProfiloAnimale = findViewById(R.id.modificaBTN);
        TextView cancellaprofiloAnimale = findViewById(R.id.cancellaBTN);
        ImageButton apriCartellaClinica = findViewById(R.id.cartellaClinicaBTN);

        //Set Text
        tvNomeAnimale.setText(animale.getNome());
        tvSessoAnimale.setText(animale.isSesso());
        tvAltezzaAnimale.setText(animale.getAltezza());
        tvPesoAnimale.setText(animale.getPeso());
        tvNoteAnimale.setText(animale.getNote());

        //logica database?
        modificaProfiloAnimale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfiloAnimaleActivity.this, RegistrazioneAnimaleActivity.class);
                /*
                intent.putExtra("NOME", tvNomeAnimale.getText());
                intent.putExtra("PESO", tvPesoAnimale.getText());
                intent.putExtra("ALTEZZA", tvAltezzaAnimale.getText());
                intent.putExtra("NOTE", tvNoteAnimale.getText());
                intent.putExtra("SESSO", tvSessoAnimale.getText());
                intent.putExtra("DATANASCITA", tvDataNascita.getText());
                 */
                intent.putExtra("ActivityCaller", "ProfiloAnimaleActivity");
                intent.putExtra("ANIMALE", animale);
                startActivity(intent);
            }
        });

        apriCartellaClinica.setOnClickListener(v ->{
            Intent intent = new Intent(ProfiloAnimaleActivity.this, CartellaClinicaActivity.class);
            startActivity(intent);
        });
    }
}