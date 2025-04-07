package com.example.apppet.Activities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.BounceInterpolator;
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

    Animale animale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_animale);


        animale = getIntent().getParcelableExtra("ANIMALE");
        long idAnimale = getIntent().getLongExtra("IdAnimale", 0);
        System.out.println("Nome animale: " + idAnimale);


        //findviewbyid
        TextView tvNomeAnimale = findViewById(R.id.nomeProfiloAnimale);
        TextView tvSessoAnimale = findViewById(R.id.sessoProfiloAnimale);
        TextView tvAltezzaAnimale = findViewById(R.id.altezzaProfiloAnimale);
        TextView tvPesoAnimale = findViewById(R.id.pesoProfiloAnimale);
        TextView tvNoteAnimale = findViewById(R.id.noteProfiloAnimale);
        TextView tvDataNascita = findViewById(R.id.dataProfiloAnimale);

        ImageButton heart1 = findViewById(R.id.first_heart);
        if (animale.getRatingAnimale() >= 1)
            heart1.setImageDrawable(getResources().getDrawable(R.drawable.full_heart));
        ImageButton heart2 = findViewById(R.id.second_heart);
        if (animale.getRatingAnimale() >= 2)
            heart2.setImageDrawable(getResources().getDrawable(R.drawable.full_heart));
        ImageButton heart3 = findViewById(R.id.third_heart);
        if (animale.getRatingAnimale() >= 3)
            heart3.setImageDrawable(getResources().getDrawable(R.drawable.full_heart));

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

        apriCartellaClinica.setOnClickListener(v -> {
            Intent intent = new Intent(ProfiloAnimaleActivity.this, CartellaClinicaActivity.class);
            intent.putExtra("IdAnimale", idAnimale);
            startActivity(intent);
        });

        btnBackHome.setOnClickListener(v -> {
            Intent intent = new Intent(ProfiloAnimaleActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        cancellaprofiloAnimale.setOnClickListener(v -> {
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<RegisterResponse> call = apiService.rimuoviAnimale(idAnimale);
            System.out.println("idAnimale: " + idAnimale);

            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.isSuccessful()) {
                        RegisterResponse registerResponse = response.body();
                        if (registerResponse != null && "Animale cancellato".equals(registerResponse.getMessage())) {
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

        ratingAnimation(heart1);
        ratingAnimation(heart2);
        ratingAnimation(heart3);

    }

    public void ratingAnimation(ImageButton btn) {
        btn.setOnClickListener(v -> {
            btn.setImageDrawable(getResources().getDrawable(R.drawable.full_heart));
            ObjectAnimator scalex = ObjectAnimator.ofFloat(btn, "scaleX", 1f, 1.5f, 1f);
            ObjectAnimator scaley = ObjectAnimator.ofFloat(btn, "scaleY", 1f, 1.5f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(scalex, scaley);
            animatorSet.setInterpolator(new BounceInterpolator());
            animatorSet.setDuration(800);

            animale.setRatingAnimale(animale.getRatingAnimale() + 1);

            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<Animale> call = apiService.modificaAnimale(animale);

            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<Animale> call, Response<Animale> response) {
                    if (response.isSuccessful()) {
                        Animale animale = response.body();

                        if (animale != null) {
                            Toast.makeText(ProfiloAnimaleActivity.this, "Modifica effettuata", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ProfiloAnimaleActivity.this, "Errore nella modifica", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ProfiloAnimaleActivity.this, "connessione fallita(primo if)", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Animale> call, Throwable t) {
                    Toast.makeText(ProfiloAnimaleActivity.this, "Impossibile connettersi al server", Toast.LENGTH_SHORT).show();
                }

            });

            animatorSet.start();
        });
    }

}