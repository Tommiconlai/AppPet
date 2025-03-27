package com.example.apppet.Activities;

import static com.example.apppet.Activities.HomeActivity.animaliLista;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

    Animale a1 = new Animale(null, 0, null, null, null, null);

    SharedPreferences sharedPreferences;
    long idUtente;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione_animale);
        sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);
        idUtente = sharedPreferences.getLong("userId", 0);

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
        if (callerActivity != null && callerActivity.equals("HomeActivity")) {
            btnRegistraAnimale.setVisibility(View.VISIBLE);
        }

        if (callerActivity != null && callerActivity.equals("ProfiloAnimaleActivity")) {

            btnConfermaModifiche.setVisibility(View.VISIBLE);
            nomeAnimaleET.setText(animale.getNome());
            pesoAnimaleET.setText(animale.getPeso());
            noteAnimaleET.setText(animale.getNote());
            altezzaAnimaleET.setText(animale.getAltezza());
            if (animale.isSesso().equals("M"))
                sessoM.setChecked(true);
            else if (animale.isSesso().equals("F"))
                sessoF.setChecked(true);

            sessoM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (sessoM.isChecked()) {
                        animale.setSesso("M");
                    }
                }
            });

            sessoF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (sessoF.isChecked()) {
                        animale.setSesso("F");
                    }
                }
            });

        }


        sessoM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (sessoM.isChecked()) {
                    a1.setSesso("M");
                }
            }
        });

        sessoF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (sessoF.isChecked()) {
                    a1.setSesso("F");
                }
            }
        });


        btnConfermaModifiche.setOnClickListener(v -> {
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
                String nomeAnimale = nomeAnimaleET.getText().toString();
                String pesoAnimale = pesoAnimaleET.getText().toString();
                String altezzaAnimale = altezzaAnimaleET.getText().toString();
                String noteAnimale = noteAnimaleET.getText().toString();
                String sessoAnimale;
                if (sessoM.isChecked()) {
                    sessoAnimale = "M";
                } else {
                    sessoAnimale = "F";
                }

                registerAnimale(nomeAnimale, altezzaAnimale, pesoAnimale, noteAnimale, sessoAnimale);


            }
        });
    }

    private void registerAnimale(String nomeAnimale, String altezzaAnimale, String pesoAnimale, String noteAnimale, String sessoAnimale) {

        if (nomeAnimale.isEmpty() || altezzaAnimale.isEmpty() || pesoAnimale.isEmpty()) {
            Toast.makeText(RegistrazioneAnimaleActivity.this, "i campi obbligatori", Toast.LENGTH_SHORT).show();
            return;
        }

        a1.setNome(nomeAnimale);
        a1.setRatingAnimale(0);
        a1.setPeso(pesoAnimale);
        a1.setAltezza(altezzaAnimale);
        a1.setNote(noteAnimale);
        a1.setIdutente(idUtente);
        animaliLista.add(a1);
        System.out.println("Nome animale: " + a1.getIdutente());

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<RegisterResponse> call = apiService.registrazioneAnimale(a1);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null && "Animale registrato con successo".equals(registerResponse.getMessage())) {
                        Toast.makeText(RegistrazioneAnimaleActivity.this, "Registrazione effettuata", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistrazioneAnimaleActivity.this, HomeActivity.class);
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


