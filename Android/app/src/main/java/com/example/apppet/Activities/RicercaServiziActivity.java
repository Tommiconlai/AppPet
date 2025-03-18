package com.example.apppet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppet.R;
import com.example.apppet.servizio.Servizio;
import com.example.apppet.servizio.ServizioAdapter;

import java.util.ArrayList;

public class RicercaServiziActivity extends AppCompatActivity implements RecyclerViewInterface {

    private ServizioAdapter adapter;
    private ArrayList<Servizio> listaServizi = new ArrayList<>();

    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca_servizi);

        //CREO L'AUTOCOMPLETE DEI TIPI DI SERVIZIO E SETTO L'ADAPTER
        autoCompleteTextView = findViewById(R.id.AutoCompltxt);
        String [] itemArray = getResources().getStringArray(R.array.ListaServizi);
        ArrayAdapter <String> arrayAdapter = new ArrayAdapter<String>(RicercaServiziActivity.this, android.R.layout.simple_list_item_1,itemArray);
        autoCompleteTextView.setAdapter(arrayAdapter);
        


        //USO LA RECYCLERVIEW
        RecyclerView recyclerViewServ = findViewById(R.id.listviewServ);

        //GENERO SERVIZI DI PROVA
        // Creazione dati fittizi
        Servizio s1 = new Servizio(1, "Toilettatura", "Taglio e piega", "HairConditioner", "Via Roma", 10, "9:30");
        Servizio s2 = new Servizio(2, "Addestratore", "Addestramento cane", "CaneWay", "Via Milano", 25, "9:30");
        Servizio s3 = new Servizio(3, "Allevamento", "Pensione per cani di ogni tipo", "Dott. Rossi", "Corso Italia", 50, "9:30");

        listaServizi.add(s1);
        listaServizi.add(s2);
        listaServizi.add(s3);


        //SETTO IL SERVIZIO_ADAPTER E SPECIFICO L'INTERFCCIA
        adapter = new ServizioAdapter(this, listaServizi, this);
        recyclerViewServ.setAdapter(adapter);
        recyclerViewServ.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(RicercaServiziActivity.this, ProfiloServizio.class);

        //INTENT DEL PUT EXTRA PER SETTARE L'INSERIMENTO DEI CAMPI

        intent.putExtra("NOME", listaServizi.get(position).getNome_attivita());
        intent.putExtra("DESCRIZIONE", listaServizi.get(position).getDescrizione());
        intent.putExtra("FORNITORE", listaServizi.get(position).getFornitore());
        intent.putExtra("INDIRIZZO", listaServizi.get(position).getIndirizzo());
        intent.putExtra("NUMERO CIVICO", listaServizi.get(position).getNumerocivico());
        intent.putExtra("ORARIO", listaServizi.get(position).getOrario());

        startActivity(intent);
    }
}
