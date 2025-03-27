package com.example.apppet.Activities;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.calendario.DBOpenHelper;
import com.example.apppet.cartellaClinica.CustomAdapterCC;
import com.example.apppet.cartellaClinica.LogCartellaClinica;
import com.example.apppet.R;

import java.util.ArrayList;
import java.util.List;

public class CartellaClinicaActivity extends AppCompatActivity {
    private List<LogCartellaClinica> lista = new ArrayList<>();
    private CustomAdapterCC adapter;
    private DBOpenHelper dbOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartella_clinica);

        //al posto di questa lista ci vorrebbe il database
       List <LogCartellaClinica> lista = new ArrayList<LogCartellaClinica>();

        //List <LogCartellaClinica> lista = new ArrayList<>();

        ListView listviewLogClinica=findViewById(R.id.listaCartellaClinica);
        lista.add(new LogCartellaClinica(1,"Evento 1", "01/03/2025", "Descrizione evento 1"));
        lista.add(new LogCartellaClinica(2,"Evento 2", "02/03/2025", "Descrizione evento 2"));
        lista.add(new LogCartellaClinica(3,"Evento 3", "03/03/2025", "Descrizione evento 3"));

        CustomAdapterCC adapter = new CustomAdapterCC(this, lista);
        listviewLogClinica.setAdapter(adapter);


    }
}