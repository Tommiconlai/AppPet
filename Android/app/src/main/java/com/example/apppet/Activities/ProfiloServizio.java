package com.example.apppet.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apppet.R;

public class ProfiloServizio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_servizio);

        String nomeServ = getIntent().getStringExtra("NOME");
        String descrServ = getIntent().getStringExtra("DESCRIZIONE");
        String fornServ = getIntent().getStringExtra("FORNITORE");
        String indirServ = getIntent().getStringExtra("INDIRIZZO");
        String numcServ = getIntent().getStringExtra("NUMERO CIVICO");
        String orarioServ = getIntent().getStringExtra("ORARIO");

        TextView tvNome = findViewById(R.id.NomeProfAtt);
        TextView tvDesc = findViewById(R.id.DescrProfAtt);
        TextView tvForn = findViewById(R.id.FornitProfAtt);
        TextView tvIndir = findViewById(R.id.IndirizzoProfAtt);
        TextView tvNumc = findViewById(R.id.Cap);
        TextView tvOrario = findViewById(R.id.OrarioProfAtt);

        tvNome.setText(nomeServ);
        tvDesc.setText(descrServ);
        tvForn.setText(fornServ);
        tvIndir.setText(indirServ);
        tvNumc.setText(numcServ);
        tvOrario.setText(orarioServ);


    }
}