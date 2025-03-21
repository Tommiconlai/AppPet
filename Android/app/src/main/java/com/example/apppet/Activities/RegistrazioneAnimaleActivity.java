package com.example.apppet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
        EditText dataNatAnimaleET = findViewById(R.id.dataNascitaAnimaleEditText);
        EditText pesoAnimaleET = findViewById(R.id.pesoAnimaleEditText);
        EditText altezzaAnimaleET = findViewById(R.id.altezzaAnimaleEditText);
        EditText noteAnimaleET = findViewById(R.id.noteAnimaleEditText);

        Animale animale = getIntent().getParcelableExtra("ANIMALE");
        /*
        String nomeAnimale = getIntent().getStringExtra("NOME");
        String pesoAnimale = getIntent().getStringExtra("PESO");
        String altezzaAnimale = getIntent().getStringExtra("ALTEZZA");
        String noteAnimale = getIntent().getStringExtra("NOTE");
        String sessoAnimale = getIntent().getStringExtra("SESSO");
        String dataNascita = getIntent().getStringExtra("DATANASCITA");
         */

        nomeAnimaleET.setText(animale.getNome());
        dataNatAnimaleET.setText(animale.getDataNascita());
        pesoAnimaleET.setText(animale.getPeso());
        noteAnimaleET.setText(animale.getNote());
        altezzaAnimaleET.setText(animale.getAltezza());
        if(animale.getSesso().equals("M"))
            sessoM.setActivated(true);
        else if(animale.getSesso().equals("F"))
            sessoF.setActivated(true);

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


        Button btnConferma = findViewById(R.id.btnConferma);
        btnConferma.setOnClickListener(v ->{
            Intent intent = new Intent(RegistrazioneAnimaleActivity.this, ProfiloAnimaleActivity.class);
            animale.setNome(nomeAnimaleET.getText().toString());
            animale.setNote(noteAnimaleET.getText().toString());
            animale.setPeso(pesoAnimaleET.getText().toString());
            animale.setAltezza(altezzaAnimaleET.getText().toString());
            animale.setDataNascita(dataNatAnimaleET.getText().toString());
            intent.putExtra("ANIMALE", animale);
            startActivity(intent);
        });
    }
}