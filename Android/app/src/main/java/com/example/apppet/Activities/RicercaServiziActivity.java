package com.example.apppet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
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
    private ArrayList<Servizio> listaServizi = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_servizi);

        RecyclerView recyclerViewServ = findViewById(R.id.listviewServ);

        // Creazione dati fittizi
        Servizio s1 = new Servizio("Toilettatura", "Taglio e piega", "HairConditioner", "Via Roma", 10, "9:30" );
        Servizio s2 = new Servizio("Addestratore", "Addestramento cane", "CaneWay", "Via Milano", 25, "9:30" );
        Servizio s3 = new Servizio("Allevamento", "Pensione per cani di ogni tipo", "Dott. Rossi", "Corso Italia", 50, "9:30" );

        listaServizi.add(s1);
        listaServizi.add(s2);
        listaServizi.add(s3);

        adapter = new ServizioAdapter(this, listaServizi, this);
        recyclerViewServ.setAdapter(adapter);
        recyclerViewServ.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(RicercaServiziActivity.this, ProfiloServizio.class);

        intent.putExtra("NOME", listaServizi.get(position).getNome_attivita());
        intent.putExtra("DESCRIZIONE", listaServizi.get(position).getDescrizione());
        intent.putExtra("FORNITORE", listaServizi.get(position).getFornitore());
        intent.putExtra("INDIRIZZO", listaServizi.get(position).getIndirizzo());
        intent.putExtra("NUMERO CIVICO", listaServizi.get(position).getNumerocivico());
        intent.putExtra("ORARIO", listaServizi.get(position).getOrario());

        startActivity(intent);
    }
}
