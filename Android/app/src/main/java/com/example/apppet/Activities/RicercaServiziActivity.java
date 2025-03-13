package com.example.apppet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppet.R;
import com.example.apppet.RecyclerViewInterface;
import com.example.apppet.Servizio;
import com.example.apppet.ServizioAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RicercaServiziActivity extends AppCompatActivity implements RecyclerViewInterface {

    private ServizioAdapter adapter;
    private List<Servizio> listaServizi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_servizi);

        RecyclerView recyclerViewServ = findViewById(R.id.listviewServ);

        adapter = new ServizioAdapter(this, listaServizi, this);
        recyclerViewServ.setAdapter(adapter);
        recyclerViewServ.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));


        RecyclerView listView = findViewById(R.id.listviewServ);

        // Creazione dati fittizi
        listaServizi = new ArrayList<>();
        listaServizi.add(new Servizio("Toilettatura", "Taglio e piega", "HairConditioner", "Via Roma", 10, "9:30" ));
        listaServizi.add(new Servizio("Addestratore", "Addestramento cane", "CaneWay", "Via Milano", 25, "9:30" ));
        listaServizi.add(new Servizio("Allevamento", "Pensione per cani di ogni tipo", "Dott. Rossi", "Corso Italia", 50, "9:30" ));


    }

    @Override
    public void onItemClicked(int position) {

        Intent intent = new Intent(RicercaServiziActivity.this, ProfiloServizio.class);

        String nome_attivita = getIntent().getStringExtra("NOME");
        String descrizione = getIntent().getStringExtra("DESCRIZIONE");
        String fornitore = getIntent().getStringExtra("FORNITORE");
        String indirizzo = getIntent().getStringExtra("INDIRIZZO");
        String numerocivico = getIntent().getStringExtra("NUMERO CIVICO");
        String orario = getIntent().getStringExtra("ORARIO");





        startActivity(intent);



    }


}
