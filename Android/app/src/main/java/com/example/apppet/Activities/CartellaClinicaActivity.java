package com.example.apppet.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.util.Log;

import com.example.apppet.ApiService;
import com.example.apppet.RetrofitClient;
import com.example.apppet.calendario.DBOpenHelper;
import com.example.apppet.cartellaClinica.CustomAdapterCC;
import com.example.apppet.cartellaClinica.LogCartellaClinica;
import com.example.apppet.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartellaClinicaActivity extends AppCompatActivity {
    private List<LogCartellaClinica> lista = new ArrayList<>();
    private CustomAdapterCC adapter;

    ApiService apiService = RetrofitClient.getClient().create(ApiService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartella_clinica);

        ListView listviewLogClinica = findViewById(R.id.listaCartellaClinica);
        adapter = new CustomAdapterCC(this, lista);
        listviewLogClinica.setAdapter(adapter);

        loadClinicData();

        ImageButton addDataButton = findViewById(R.id.add_Data);

        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddClinicData();
            }
        });

        /*
        //al posto di questa lista ci vorrebbe il database
       List <LogCartellaClinica> lista = new ArrayList<LogCartellaClinica>();

        //List <LogCartellaClinica> lista = new ArrayList<>();

        ListView listviewLogClinica=findViewById(R.id.listaCartellaClinica);
        lista.add(new LogCartellaClinica(1,"Evento 1", "01/03/2025", "Descrizione evento 1"));
        lista.add(new LogCartellaClinica(2,"Evento 2", "02/03/2025", "Descrizione evento 2"));
        lista.add(new LogCartellaClinica(3,"Evento 3", "03/03/2025", "Descrizione evento 3"));

        CustomAdapterCC adapter = new CustomAdapterCC(this, lista);
        listviewLogClinica.setAdapter(adapter);

         */


    }

    private void showDialogToAddClinicData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_new_clinic_data_layout, null);
        builder.setView(dialogView);

        final EditText dataNameEditText = dialogView.findViewById(R.id.dataName);

        builder.setPositiveButton("Aggiungi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String data = dataNameEditText.getText().toString().trim();
                if (!data.isEmpty()) {
                    addNewClinicData(data);
                } else {
                    Toast.makeText(CartellaClinicaActivity.this, "Inserisci i dati clinici", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Annulla", null);

        builder.create().show();
    }

    private void loadClinicData() {
        apiService.getAllClinicLogs().enqueue(new Callback<ArrayList<LogCartellaClinica>>() {
            @Override
            public void onResponse(Call<ArrayList<LogCartellaClinica>> call, Response<ArrayList<LogCartellaClinica>> response) {
                if (response.isSuccessful()) {
                    lista = response.body();
                    adapter.setData(lista);
                } else {
                    Toast.makeText(CartellaClinicaActivity.this, "Errore nel caricamento dei dati", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<LogCartellaClinica>> call, Throwable t) {
                Toast.makeText(CartellaClinicaActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void addNewClinicData(String data) {
        LocalDate today = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            today = LocalDate.now();
        }
        LogCartellaClinica newLog = new LogCartellaClinica(0, data, today.toString(), ""); // ID 0 o un valore temporaneo, l'API dovrebbe assegnare l'ID
        apiService.createClinicLog(newLog).enqueue(new Callback<LogCartellaClinica>() {
            @Override
            public void onResponse(Call<LogCartellaClinica> call, Response<LogCartellaClinica> response) {
                if (response.isSuccessful()) {
                    LogCartellaClinica createdLog = response.body();
                    lista.add(createdLog); // Aggiungi il log restituito dall'API (con l'ID assegnato)
                    adapter.setData(lista);
                    Toast.makeText(CartellaClinicaActivity.this, "Dati aggiunti", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CartellaClinicaActivity.this, "Errore nell'aggiunta dei dati", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogCartellaClinica> call, Throwable t) {
                Toast.makeText(CartellaClinicaActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}