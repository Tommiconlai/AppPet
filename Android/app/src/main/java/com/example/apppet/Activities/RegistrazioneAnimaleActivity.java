package com.example.apppet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.ApiService;
import com.example.apppet.R;
import com.example.apppet.RetrofitClient;
import com.example.apppet.RegisterResponse;
import com.example.apppet.animale.Animale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrazioneAnimaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione_animale);

        EditText nomeAnimaleET = findViewById(R.id.nomeAnimaleEditText);
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
                //btnConfermaModifiche.setVisibility(View.VISIBLE);
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
        btnRegistraAnimale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeAnimale=nomeAnimaleET.getText().toString();
                String pesoAnimale=pesoAnimaleET.getText().toString();
                String altezzaAnimale=altezzaAnimaleET.getText().toString();
                String noteAnimale=noteAnimaleET.getText().toString();
                String sessoAnimale;
                if(sessoM.isChecked()){ sessoAnimale="M"; }
                else{ sessoAnimale="F"; }


            }
        });
    }
    private void registerAnimale(String nomeAnimale, String altezzaAnimale, String pesoAnimale, String noteAnimale, String sessoAnimale) {

        if (nomeAnimale.isEmpty() || altezzaAnimale.isEmpty() || pesoAnimale.isEmpty()) {
            Toast.makeText(RegistrazioneAnimaleActivity.this, "i campi obbligatori", Toast.LENGTH_SHORT).show();
            return;
        }
        Animale a1 = new Animale(nomeAnimale,0,pesoAnimale,altezzaAnimale,noteAnimale,sessoAnimale);
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<RegisterResponse> call = apiService.registrazioneAnimale(a1);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null && "Utente registrato con successo".equals(registerResponse.getMessage())) {
                        Toast.makeText(RegistrazioneAnimaleActivity.this, "Registrazione effettuata", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistrazioneAnimaleActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegistrazioneAnimaleActivity.this, "Errore nella registrazione", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistrazioneAnimaleActivity.this, "connessione fallita(primo if)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegistrazioneAnimaleActivity.this, "Impossibile connettersi al server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


