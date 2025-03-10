package com.example.apppet;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListaServizioActivity extends AppCompatActivity {
    private ListView listView;
    private ServizioAdapter adapter;
    private List<Servizio> listaServizi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Creazione dati fittizi
        listaServizi = new ArrayList<>();
        listaServizi.add(new Servizio("Toilettatura", "Taglio e piega", "HairConditioner", "Via Roma", 10, getOrario(9, 30)));
        listaServizi.add(new Servizio("Addestratore", "Addestramento cane", "CaneWay", "Via Milano", 25, getOrario(14, 0)));
        listaServizi.add(new Servizio("Allevamento", "Pensione per cani di ogni tipo", "Dott. Rossi", "Corso Italia", 50, getOrario(16, 15)));

        adapter = new ServizioAdapter(this, listaServizi);
        listView.setAdapter(adapter);
    }

    private Date getOrario(int ore, int minuti) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, ore);
        calendar.set(Calendar.MINUTE, minuti);
        return calendar.getTime();
    }
}




