package com.example.apppet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

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
            TextView txtDate = convertView.findViewById(R.id.txtDate);
            TextView txtDescription = convertView.findViewById(R.id.txtDescription);

            LogCartellaClinica currentItem = lista.get(position);

            txtTitle.setText(currentItem.getTitle());
            txtDate.setText(currentItem.getDate());
            txtDescription.setText(currentItem.getDescription());

            return convertView;
        }
    }


