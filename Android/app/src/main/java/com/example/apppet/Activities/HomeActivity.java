package com.example.apppet.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppet.ApiService;
import com.example.apppet.RegisterResponse;
import com.example.apppet.RetrofitClient;
import com.example.apppet.animale.Animale;
import com.example.apppet.animale.ListaAnimaliAdapter;
import com.example.apppet.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements RecyclerViewInterface {
    private ListaAnimaliAdapter adapterAnimali;
    public static ArrayList<Animale> animaliLista = new ArrayList<>();
    public RecyclerView recyclerAnimali;

    SharedPreferences sharedPreferences;
    long idutente;

    RatingBar ratingAnimale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);

        recyclerAnimali = findViewById(R.id.animali_recycler_view);
        idutente = sharedPreferences.getLong("userId", 0);

        inizializzaAnimali();

        ImageButton btn_goToProfile = findViewById(R.id.BTNprofilo);
        btn_goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfiloUtenteActivity.class);
                startActivity(intent);
            }
        });

        ImageButton nuovaAttivita = findViewById(R.id.add_attività_BTN);
        nuovaAttivita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Calendario_attivita.class);
                startActivity(intent);
            }
        });

        ImageButton cercaServizi = findViewById(R.id.cerca_servizi_BTN);
        cercaServizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RicercaServiziActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btnAggiungiAnimale = findViewById(R.id.add_animale_BTN);
        btnAggiungiAnimale.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, RegistrazioneAnimaleActivity.class);
            intent.putExtra("ActivityCaller", "HomeActivity");
            startActivity(intent);
        });
    }


    public void inizializzaAnimali() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ArrayList<Animale>> call = apiService.listaAnimali(idutente);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ArrayList<Animale>> call, Response<ArrayList<Animale>> response) {
                animaliLista = response.body();
                adapterAnimali = new ListaAnimaliAdapter(HomeActivity.this, animaliLista, HomeActivity.this);
                recyclerAnimali.setAdapter(adapterAnimali);
                recyclerAnimali.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
                for (Animale animale : animaliLista) {
                    System.out.println("Nome animale: " + animale.getId());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Animale>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Impossibile connettersi al server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(HomeActivity.this, ProfiloAnimaleActivity.class);

        intent.putExtra("ANIMALE", animaliLista.get(position));
        intent.putExtra("IdAnimale", animaliLista.get(position).getId());

        startActivity(intent);
    }
}
