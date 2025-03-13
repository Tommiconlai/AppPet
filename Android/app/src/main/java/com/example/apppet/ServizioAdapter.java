package com.example.apppet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

    public class ServizioAdapter extends RecyclerView.Adapter<ServizioAdapter.ViewHolder> {
        private Context context;
        private List<Servizio> servizi;
        private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        private final RecyclerViewInterface recyclerViewInterface;

        public ServizioAdapter(Context context, List<Servizio> servizi, RecyclerViewInterface recyclerViewInterface) {
            this.context = context;
            this.servizi = servizi;
            this.recyclerViewInterface = recyclerViewInterface;

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_servizio, parent, false);
            return new ViewHolder(view, recyclerViewInterface);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Servizio servizio = servizi.get(position);
            holder.tvNome.setText(servizio.getNome_attivita());
            holder.tvDescr.setText(servizio.getDescrizione());
            holder.tvFornitore.setText(servizio.getFornitore());
            holder.tvIndirizzo.setText(servizio.getIndirizzo());
            holder.tvOrario.setText(servizio.getOrario());

        }

        @Override
        public int getItemCount() {
            return servizi.size();
        }


        /*
           @Override
           public View getView(int position, View convertView, ViewGroup parent) {
               if (convertView == null) {
                   convertView = LayoutInflater.from(context).inflate(R.layout.item_servizio, parent, false);
               }

               Servizio servizio = servizi.get(position);

               TextView tvNomeAttivita = convertView.findViewById(R.id.tvNomeAttivita);
               TextView tvDescrizione = convertView.findViewById(R.id.tvDescrizione);
               TextView tvFornitore = convertView.findViewById(R.id.tvFornitore);
               TextView tvIndirizzo = convertView.findViewById(R.id.tvIndirizzo);
               TextView tvOrario = convertView.findViewById(R.id.tvOrario);

               tvNomeAttivita.setText(servizio.getNome_attivita());
               tvDescrizione.setText(servizio.getDescrizione());
               tvFornitore.setText("Fornitore: " + servizio.getFornitore());
               tvIndirizzo.setText("Indirizzo: " + servizio.getIndirizzo() + ", " + servizio.getNumerocivico());
               tvOrario.setText("Orario: " + dateFormat.format(servizio.getOrario()));

               return convertView;
           }



         */
        public static class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvNome;
            TextView tvDescr;

            TextView tvIndirizzo;

            TextView tvFornitore;

            TextView tvOrario;


            public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewListaAnimaliInterface) {
                super(itemView);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (recyclerViewListaAnimaliInterface != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                recyclerViewListaAnimaliInterface.onItemClicked(position);
                            }
                        }
                    }
                });
            }

        }



    }


