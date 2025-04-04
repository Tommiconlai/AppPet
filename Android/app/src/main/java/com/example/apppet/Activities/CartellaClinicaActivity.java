package com.example.apppet.Activities;

import static androidx.core.content.IntentCompat.getParcelableExtra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
    private ArrayList<LogCartellaClinica> lista = new ArrayList<>();
    private CustomAdapterCC adapter;

    ListView listviewLogClinica;

    long idAnimale;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartella_clinica);

        listviewLogClinica = findViewById(R.id.listaCartellaClinica);
        adapter = new CustomAdapterCC(this, lista);
        listviewLogClinica.setAdapter(adapter);
        idAnimale = getIntent().getLongExtra("IdAnimale", -1);
        System.out.println("ID Animale: " + idAnimale);
        ImageButton add = findViewById(R.id.add_Data);
        Button back = findViewById(R.id.indietro);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartellaClinicaActivity.this, ProfiloAnimaleActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddClinicData();

            }

        });

        inizializzaListaCartella();



    }

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
                    addCartellaClinicaToList(title, description);
                } else {
                    Toast.makeText(CartellaClinicaActivity.this, "Inserisci i dati clinici", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Annulla", null);

        builder.create().show();
    }

    private void addCartellaClinicaToList(String titolo,  String descrizione) {
        // Controlla se l'ID dell'animale è valido
        if (idAnimale <= 0) {
            // Se l'ID non è valido, mostra un messaggio di errore
            Toast.makeText(CartellaClinicaActivity.this, "ID animale non valido", Toast.LENGTH_SHORT).show();
            return; // Esce dal metodo senza inviare la richiesta
        }
        LogCartellaClinica logCartellaClinica = new LogCartellaClinica(titolo, descrizione, idAnimale);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<RegisterResponse> call = apiService.salvaCartellaClinica(logCartellaClinica);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    Log.i("ciaociao", registerResponse.getMessage());
                    if (registerResponse != null && "Cartella clinica salvata con successo".equals(registerResponse.getMessage())) {
                        Toast.makeText(CartellaClinicaActivity.this, "Cartella clinica salvata con successo", Toast.LENGTH_SHORT).show();
                        lista.add(logCartellaClinica);
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(CartellaClinicaActivity.this, "Errore nel salvataggio della cartella", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(CartellaClinicaActivity.this, "connessione fallita(primo if)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(CartellaClinicaActivity.this, "Errore di connessione", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void inizializzaListaCartella(){
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ArrayList<LogCartellaClinica>> call = apiService.listaCartelleCliniche(idAnimale);
        lista = new ArrayList<>();

        call.enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<ArrayList<LogCartellaClinica>> call, Response<ArrayList<LogCartellaClinica>> response) {
                lista = response.body();
                if (response.body() != null){
                    adapter = new CustomAdapterCC(CartellaClinicaActivity.this, lista);
                    listviewLogClinica = findViewById(R.id.listaCartellaClinica);
                    listviewLogClinica.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(CartellaClinicaActivity.this, "Body vuoto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<LogCartellaClinica>> call, Throwable t) {
                Toast.makeText(CartellaClinicaActivity.this, "Impossibile connettersi al server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}