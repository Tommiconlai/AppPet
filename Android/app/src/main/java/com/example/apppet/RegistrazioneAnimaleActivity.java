package com.example.apppet;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrazioneAnimaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione_animale);
        EditText nomeAnimaleET = findViewById(R.id.nomeAnimaleEditText);
        RadioGroup sessoAnimaleET = findViewById(R.id.sessoAnimaleRadioBTN);
        EditText dataNatAnimaleET = findViewById(R.id.dataNascitaAnimaleEditText);
        EditText pesoAnimaleET = findViewById(R.id.pesoAnimaleEditText);
        EditText noteAnimaleET = findViewById(R.id.noteAnimaleEditText);




    }
}