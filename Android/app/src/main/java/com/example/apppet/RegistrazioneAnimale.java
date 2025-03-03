package com.example.apppet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrazioneAnimale extends AppCompatActivity {

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