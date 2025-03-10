package com.example.apppet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.R;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
}
