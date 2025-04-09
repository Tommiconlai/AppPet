package com.example.apppet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
        Button btnBackHome = findViewById(R.id.btnBackHome);

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendario_attivita.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}




