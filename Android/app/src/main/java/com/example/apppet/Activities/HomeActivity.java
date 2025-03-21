package com.example.apppet.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppet.animale.Animale;
import com.example.apppet.animale.ListaAnimaliAdapter;
import com.example.apppet.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements RecyclerViewInterface {
    private ListaAnimaliAdapter adapterAnimali;
    private ArrayList<Animale> animaliLista = new ArrayList<>();

    RatingBar ratingAnimale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerAnimali = findViewById(R.id.animali_recycler_view);

        inizializzaAnimali();

        adapterAnimali = new ListaAnimaliAdapter((Context) this, (ArrayList<Animale>) animaliLista, this);
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

        ImageButton btnAggiungiAnimale = findViewById(R.id.add_animale_BTN);
        btnAggiungiAnimale.setOnClickListener(v ->{
            Intent intent = new Intent(HomeActivity.this, RegistrazioneAnimaleActivity.class);
            intent.putExtra("ARRIVO", false);
            startActivity(intent);
        });
    }


    public void inizializzaAnimali(){
        //Dati finti di prova
        Animale animale1 = new Animale("Fuffi", 1, "30", "35", "Barboncino un po' cresciuto", "M", "12/03/2023");
        Animale animale2 = new Animale("Pippo", 2, "43", "57", "Labrador che sbava ovunque", "F", "01/05/2019");
        Animale animale3 = new Animale("Demetrio", 2.5f, "12", "22", "Persiano liscio liscio", "M", "25/10/2021");

        animaliLista.add(animale1);
        animaliLista.add(animale2);
        animaliLista.add(animale3);
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(HomeActivity.this, ProfiloAnimaleActivity.class);

        /*
        intent.putExtra("NOME", animaliLista.get(position).getNome());
        intent.putExtra("RATING", animaliLista.get(position).getRatingAnimale());
        intent.putExtra("PESO", animaliLista.get(position).getPeso());
        intent.putExtra("ALTEZZA", animaliLista.get(position).getAltezza());
        intent.putExtra("NOTE", animaliLista.get(position).getNote());
        intent.putExtra("SESSO", animaliLista.get(position).getSesso());
        intent.putExtra("DATANASCITA", animaliLista.get(position).getDataNascita());
         */

        intent.putExtra("ANIMALE", (Parcelable) animaliLista.get(position));

        startActivity(intent);
    }
}
