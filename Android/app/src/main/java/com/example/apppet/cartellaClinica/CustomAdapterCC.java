package com.example.apppet.cartellaClinica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apppet.Activities.CartellaClinicaActivity;
import com.example.apppet.Activities.HomeActivity;
import com.example.apppet.ApiService;
import com.example.apppet.R;
import com.example.apppet.RegisterResponse;
import com.example.apppet.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAdapterCC extends BaseAdapter {
        private Context context;
        private List<LogCartellaClinica> lista;

        public CustomAdapterCC(Context context, List<LogCartellaClinica> lista) {
            this.context = context;
            this.lista = lista;
        }

        @Override
        public int getCount() {
            return lista.size();
        }

        @Override
        public Object getItem(int position) {
            return lista.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.listviewclinica, parent, false);
            }

            TextView txtTitle = convertView.findViewById(R.id.txtTitle);
            TextView txtDescription = convertView.findViewById(R.id.txtDescription);
            Button elimina = convertView.findViewById(R.id.elimina);

            elimina.setOnClickListener(v ->{
                ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
                long idLog = lista.get(position).getId();
                Call<RegisterResponse> call = apiService.rimuoviCartellaClinica(idLog);

                System.out.println("ID LOG: " + idLog);

                call.enqueue(new Callback<>() {

                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful()) {
                            RegisterResponse deletedLog = response.body();
                            if (deletedLog != null && "cartella cancellata".equals(response.body().getMessage())) {
                                lista.remove(position);
                                notifyDataSetChanged();
                            }
                            else {
                                Toast.makeText(context, "Errore nell'eliminazione della cartella clinica", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(context, "connessione fallita(primo if)", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(context, "Impossibile connettersi al server", Toast.LENGTH_SHORT).show();
                    }
                });
            });

            LogCartellaClinica currentItem = lista.get(position);

            txtTitle.setText(currentItem.getTitle());
            txtDescription.setText(currentItem.getDescription());

            return convertView;
        }
        public void setData(List<LogCartellaClinica> data) {
            this.lista = data;
            notifyDataSetChanged();
        }
    }


