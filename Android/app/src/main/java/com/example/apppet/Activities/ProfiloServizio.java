package com.example.apppet.Activities;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.R;
import com.example.apppet.RecensioniServizio.Recensione;
import com.example.apppet.RecensioniServizio.RecensioniAdapter;
import com.example.apppet.servizio.Servizio;


import java.util.ArrayList;

public class ProfiloServizio extends AppCompatActivity {

    ArrayList<Recensione> listaRec = new ArrayList<>();
    RecensioniAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_servizio);

        Recensione r1 = new Recensione("Pippo", 2.5f);

        listaRec.add(r1);

        /*
        String nomeServ = getIntent().getStringExtra("NOME");
        String indirServ = getIntent().getStringExtra("INDIRIZZO");
        String numcServ = getIntent().getStringExtra("NUMERO CIVICO");
        String orarioServ = getIntent().getStringExtra("ORARIO");

         */

        Servizio s = getIntent().getParcelableExtra("SERVIZIO");

        TextView tvNome = findViewById(R.id.tvNomeServizio);
        TextView tvIndir = findViewById(R.id.tvIndirizzoServizio);
        TextView tvCap = findViewById(R.id.tvCapServizio);
        TextView tvOrario = findViewById(R.id.tvOrarioServizio);
        ListView lvRecensioni = findViewById(R.id.lvRecensioni);

        tvNome.setText(s.getNome_attivita());
        tvIndir.setText(s.getIndirizzo());
        tvCap.setText(s.getCap());
        tvOrario.setText(s.getOrario());

        adapter = new RecensioniAdapter(this, listaRec);
        lvRecensioni.setAdapter(adapter);

    }
}