package com.example.apppet.Activities;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.R;
import com.example.apppet.animale.Animale;

public class RegistrazioneAnimaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione_animale);

        EditText nomeAnimaleET = findViewById(R.id.nomeAnimaleEditText);
        RadioGroup sessoGruppo = findViewById(R.id.sessoGruppo);
        RadioButton sessoM = findViewById(R.id.rbMaschio);
        RadioButton sessoF = findViewById(R.id.rbFemmina);
        EditText pesoAnimaleET = findViewById(R.id.pesoAnimaleEditText);
        EditText altezzaAnimaleET = findViewById(R.id.altezzaAnimaleEditText);
        EditText noteAnimaleET = findViewById(R.id.noteAnimaleEditText);
        Button btnConfermaModifiche = findViewById(R.id.btnConfermaModifiche);
        Button btnRegistraAnimale = findViewById(R.id.btnConfermaRegistrazione);


        String callerActivity = getIntent().getStringExtra("ActivityCaller");
        Animale animale = getIntent().getParcelableExtra("ANIMALE");

        System.out.println("Arrivo da: " + callerActivity);

        if(callerActivity != null){
            if(callerActivity.equals("ProfiloAnimaleActivity"))
                btnConfermaModifiche.setVisibility(View.VISIBLE);
                nomeAnimaleET.setText(animale.getNome());
                pesoAnimaleET.setText(animale.getPeso());
                noteAnimaleET.setText(animale.getNote());
                altezzaAnimaleET.setText(animale.getAltezza());
                if(animale.isSesso().equals("M"))
                    sessoM.setChecked(true);
                else if(animale.isSesso().equals("F"))
                    sessoF.setChecked(true);
        }


        /*
        String nomeAnimale = getIntent().getStringExtra("NOME");
        String pesoAnimale = getIntent().getStringExtra("PESO");
        String altezzaAnimale = getIntent().getStringExtra("ALTEZZA");
        String noteAnimale = getIntent().getStringExtra("NOTE");
        String sessoAnimale = getIntent().getStringExtra("SESSO");
        String dataNascita = getIntent().getStringExtra("DATANASCITA");
         */



        sessoM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sessoM.isChecked()){
                    animale.setSesso("M");
                }
            }
        });

        sessoF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sessoF.isChecked()){
                    animale.setSesso("F");
                }
            }
        });


        Button btnConferma = findViewById(R.id.btnConfermaModifiche);
        btnConferma.setOnClickListener(v ->{
            Intent intent = new Intent(RegistrazioneAnimaleActivity.this, ProfiloAnimaleActivity.class);
            animale.setNome(nomeAnimaleET.getText().toString());
            animale.setNote(noteAnimaleET.getText().toString());
            animale.setPeso(pesoAnimaleET.getText().toString());
            animale.setAltezza(altezzaAnimaleET.getText().toString());
            intent.putExtra("ANIMALE", animale);
            startActivity(intent);
        });
    }
}