package com.example.apppet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

    public class ServizioAdapter extends BaseAdapter {
        private Context context;
        private List<Servizio> servizi;
        private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        public ServizioAdapter(Context context, List<Servizio> servizi) {
            this.context = context;
            this.servizi = servizi;
        }

        @Override
        public int getCount() {
            return servizi.size();
        }

        @Override
        public Object getItem(int position) {
            return servizi.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

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

            tvNomeAttivita.setText(servizio.getNomeAttivita());
            tvDescrizione.setText(servizio.getDescrizione());
            tvFornitore.setText("Fornitore: " + servizio.getFornitore());
            tvIndirizzo.setText("Indirizzo: " + servizio.getIndirizzo() + ", " + servizio.getNumeroCivico());
            tvOrario.setText("Orario: " + dateFormat.format(servizio.getOrario()));

            return convertView;
        }
    }


