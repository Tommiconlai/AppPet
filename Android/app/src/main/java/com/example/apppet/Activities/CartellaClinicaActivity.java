package com.example.apppet.Activities;

import static androidx.core.content.IntentCompat.getParcelableExtra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppet.ApiService;
import com.example.apppet.RegisterResponse;
import com.example.apppet.RetrofitClient;
import com.example.apppet.animale.Animale;
import com.example.apppet.cartellaClinica.CustomAdapterCC;
import com.example.apppet.cartellaClinica.LogCartellaClinica;
import com.example.apppet.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartellaClinicaActivity extends AppCompatActivity {
    private List<LogCartellaClinica> lista = new ArrayList<>();
    private CustomAdapterCC adapter;

    long idAnimale;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartella_clinica);

        ListView listviewLogClinica = findViewById(R.id.listaCartellaClinica);
        adapter = new CustomAdapterCC(this, lista);
        listviewLogClinica.setAdapter(adapter);
        Animale animale = getIntent().getParcelableExtra("ANIMALE");
        idAnimale = getIntent().getLongExtra("IdAnimale", -1);
        System.out.println("ID Animale: " + idAnimale);
        ImageButton add = findViewById(R.id.add_Data);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddClinicData();

            }

        });

    }
//apre alert dialog per inserire i dati clinici
    private void showDialogToAddClinicData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_new_clinic_data_layout, null);
        builder.setView(dialogView);

        EditText dataNameEditText = dialogView.findViewById(R.id.dataName);
        EditText dataTitleEditText = dialogView.findViewById(R.id.dataTitle);

        builder.setPositiveButton("Aggiungi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String description = dataNameEditText.getText().toString();
                String title = dataTitleEditText.getText().toString();
                if (!description.isEmpty() && !title.isEmpty()) {
                    addCartellaClinicaToList(title, description); // Aggiungi la data effettiva qui, se disponibile
                } else {
                    Toast.makeText(CartellaClinicaActivity.this, "Inserisci i dati clinici", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Annulla", null);

        builder.create().show();
    }

    //metodo per aggiungere una nuova cartella clinica alla lista
    private void addCartellaClinicaToList(String titolo,  String descrizione) {
        // Controlla se l'ID dell'animale è valido
        if (idAnimale <= 0) {
            // Se l'ID non è valido, mostra un messaggio di errore
            Toast.makeText(CartellaClinicaActivity.this, "ID animale non valido", Toast.LENGTH_SHORT).show();
            return;
        }
        LogCartellaClinica logCartellaClinica = new LogCartellaClinica(titolo, descrizione, idAnimale);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<RegisterResponse> call = apiService.salvaCartellaClinica(logCartellaClinica);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    lista.add(logCartellaClinica);
                    adapter.notifyDataSetChanged(); // Rende visibile il nuovo dato nella ListView
                } else {
                    Toast.makeText(CartellaClinicaActivity.this, "Errore nel salvataggio della cartella", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(CartellaClinicaActivity.this, "Errore di connessione", Toast.LENGTH_SHORT).show();
            }
        });
    }
}