package com.example.apppet.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppet.Animale;
import com.example.apppet.ListaAnimaliAdapter;
import com.example.apppet.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ListaAnimaliAdapter adapterAnimali;
    private ArrayList<Animale> animaliLista = new ArrayList<>();

    RatingBar ratingAnimale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerAnimali = findViewById(R.id.animali_recycler_view);


        inizializzaAnimali();

        /*
        //Dati finti di prova
        animaliLista.add(new Animale("Fuffi", 1f));
        animaliLista.add(new Animale("Pippo", 2f));
        animaliLista.add(new Animale("Demetrio il mago del regno", 2.5f));
        */

        adapterAnimali = new ListaAnimaliAdapter((Context) this, (ArrayList<Animale>) animaliLista);
        recyclerAnimali.setAdapter(adapterAnimali);
        recyclerAnimali.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ImageButton btn_goToProfile = findViewById(R.id.BTNprofilo);
        btn_goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
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
    }


    public void inizializzaAnimali(){
        //Dati finti di prova
        Animale animale1 = new Animale("Fuffi", 1f);
        Animale animale2 = new Animale("Pippo", 2f);
        Animale animale3 = new Animale("Demetrio", 2.5f);

        animaliLista.add(animale1);
        animaliLista.add(animale2);
        animaliLista.add(animale3);
    }

}
