package com.example.apppet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.ApiService;
import com.example.apppet.R;
import com.example.apppet.RegisterResponse;
import com.example.apppet.RetrofitClient;
import com.example.apppet.animale.Animale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfiloAnimaleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_animale);


        Animale animale = getIntent().getParcelableExtra("ANIMALE");
        long idAnimale = getIntent().getLongExtra("IdAnimale", 0);
        System.out.println("Nome animale: " + idAnimale);


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
        Button btnBackHome = findViewById(R.id.btnBackHome);


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
                intent.putExtra("ActivityCaller", "ProfiloAnimaleActivity");
                intent.putExtra("ANIMALE", animale);
                intent.putExtra("IdAnimale", idAnimale);
                startActivity(intent);
            }
        });

        apriCartellaClinica.setOnClickListener(v ->{
            Intent intent = new Intent(ProfiloAnimaleActivity.this, CartellaClinicaActivity.class);
            intent.putExtra("IdAnimale", idAnimale);
            startActivity(intent);
        });

        btnBackHome.setOnClickListener(v ->{
            Intent intent = new Intent(ProfiloAnimaleActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        cancellaprofiloAnimale.setOnClickListener(v ->{
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<RegisterResponse> call = apiService.rimuoviAnimale(idAnimale);
            System.out.println("idAnimale: " + idAnimale);

            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.isSuccessful()) {
                        RegisterResponse registerResponse = response.body();
                        if (registerResponse != null && "animale cancellato".equals(registerResponse.getMessage())) {
                            Toast.makeText(ProfiloAnimaleActivity.this, "Animale cancellato correttamente", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ProfiloAnimaleActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ProfiloAnimaleActivity.this, "Errore nella risposta del server", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(ProfiloAnimaleActivity.this, "Cancella prima i dati nella cartella clinica", Toast.LENGTH_SHORT).show();

                }
            });


        });

    }
}