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

    ApiService apiService = RetrofitClient.getClient().create(ApiService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartella_clinica);

        ListView listviewLogClinica = findViewById(R.id.listaCartellaClinica);
        adapter = new CustomAdapterCC(this, lista);
        listviewLogClinica.setAdapter(adapter);
        Animale animale = getIntent().getParcelableExtra("ANIMALE");
        long id = getIntent().getLongExtra("idAnimale", -1);
        System.out.println("ID Animale: " + id);
        ImageButton add = findViewById(R.id.add_Data);



        ImageButton addDataButton = findViewById(R.id.add_Data);

        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddClinicData();
            }
        });

        //al posto di questa lista ci vorrebbe il database
       //List <LogCartellaClinica> lista = new ArrayList<LogCartellaClinica>();

        //List <LogCartellaClinica> lista = new ArrayList<>();


       //ListView listviewLogClinica=findViewById(R.id.listaCartellaClinica);
       /*
        lista.add(new LogCartellaClinica(1,"Evento 1", "01/03/2025", "Descrizione evento 1"));
        lista.add(new LogCartellaClinica(2,"Evento 2", "02/03/2025", "Descrizione evento 2"));
        lista.add(new LogCartellaClinica(3,"Evento 3", "03/03/2025", "Descrizione evento 3"));
           */

        CustomAdapterCC adapter = new CustomAdapterCC(this, lista);
        listviewLogClinica.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddClinicData();

            }

        });






    }

    private void showDialogToAddClinicData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_new_clinic_data_layout, null);
        builder.setView(dialogView);

        final EditText dataNameEditText = dialogView.findViewById(R.id.dataName);
        final EditText dataTitleEditText = dialogView.findViewById(R.id.dataTitle);

        builder.setPositiveButton("Aggiungi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String description = dataNameEditText.getText().toString().trim();
                String title = dataTitleEditText.getText().toString().trim();
                if (!description.isEmpty() && !title.isEmpty()) {
                    addCartellaClinicaToList(title, description, " "); // Aggiungi la data effettiva qui, se disponibile
                } else {
                    Toast.makeText(CartellaClinicaActivity.this, "Inserisci i dati clinici", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Annulla", null);

        builder.create().show();
    }
    /*
    private void loadClinicData() {
        apiService.listaCartelleCliniche((int) idAnimale).enqueue(new Callback<ArrayList<LogCartellaClinica>>() {
            @Override
            public void onResponse(Call<ArrayList<LogCartellaClinica>> call, Response<ArrayList<LogCartellaClinica>> response) {
                System.out.println("idAnimale" + idAnimale);
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

    private void addNewClinicData(String title, String description) {
        LocalDate today = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            today = LocalDate.now();
        }
        DateTimeFormatter formatter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
        String dateStr = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateStr = today.format(formatter);
        }

        System.out.println("ClinicData" + "Tentativo di recuperare l'ID animale da SharedPreferences");

        SharedPreferences prefs = getSharedPreferences("animal_id_pref", MODE_PRIVATE);// Sostituisci con il nome effettivo delle tue prefs

            long idAnimale = prefs.getLong("selected_animal_id", -1); // Sostituisci con la tua chiave effettiva
            if (idAnimale == -1) {
                Toast.makeText(this, "ID animale non trovato!", Toast.LENGTH_SHORT).show();
                return;
            }
        System.out.println("ClinicData" + "ID animale recuperato: " + idAnimale);

            LogCartellaClinica newLog = new LogCartellaClinica( title, dateStr, description, idAnimale); // Usa il nuovo costruttore

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

                        System.out.println("API Error" + "Code: " + response.code() + " Message: " + response.message() + ", Body: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<LogCartellaClinica> call, Throwable t) {
                    Toast.makeText(CartellaClinicaActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();

                    System.out.println("Network Error" + "Message: " + t.getMessage() + t);
                }
            });
    }

     */
    private void addCartellaClinicaToList(final String titolo, final String descrizione, final String dataAppuntamento) {
        // Controlla se l'ID dell'animale è valido (ad esempio, non può essere 0 o un valore negativo)
        if (idAnimale <= 0) {
            // Se l'ID non è valido, mostra un messaggio di errore
            Toast.makeText(CartellaClinicaActivity.this, "ID animale non valido", Toast.LENGTH_SHORT).show();
            return; // Esce dal metodo senza inviare la richiesta
        }
        LogCartellaClinica logCartellaClinica = new LogCartellaClinica(titolo, descrizione, dataAppuntamento, idAnimale);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<RegisterResponse> call = apiService.salvaCartellaClinica(logCartellaClinica);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    // Salvataggio riuscito, aggiungi la cartella alla lista e aggiorna la UI
                    lista.add(new LogCartellaClinica(titolo, descrizione, dataAppuntamento, idAnimale));
                    adapter.notifyDataSetChanged(); // Rende visibile il nuovo dato nella ListView
                } else {
                    // Gestisci l'errore, ad esempio mostrando un messaggio all'utente
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