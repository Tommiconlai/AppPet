package com.example.apppet.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.calendario.CustomCalendarView;
import com.example.apppet.R;

public class Calendario_attivita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
             setContentView(R.layout.activity_calendario);
            CustomCalendarView customCalendarView = findViewById(R.id.custom_calendar_view);
            if (customCalendarView != null) {
                customCalendarView.setVisibility(View.VISIBLE);  // Assicurati che la vista sia visibile
            } else {
                Log.e("MainActivity", "CustomCalendarView non trovata");
            }
            // Inizializza altre risorse
        } catch (Exception e) {
            e.printStackTrace(); // Questo ti aiuterà a vedere eventuali errori.
        }

    }
}