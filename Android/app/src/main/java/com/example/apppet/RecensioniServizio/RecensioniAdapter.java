package com.example.apppet.RecensioniServizio;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.apppet.R;

import java.util.ArrayList;
import java.util.List;

public class RecensioniAdapter extends BaseAdapter {

    Context context;
    List<Recensione> recensioni;

    public RecensioniAdapter(Context context, List<Recensione> recensioni) {
        this.context = context;
        this.recensioni = recensioni;
    }

    @Override
    public int getCount() {
        return recensioni.size();
    }

    @Override
    public Object getItem(int position) {
        return recensioni.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_recensione, null);
        }

        TextView nomeAutore = convertView.findViewById(R.id.tvNomeUtenteRecensione);
        RatingBar ratingBar = convertView.findViewById(R.id.rbRatingServizio);

        Recensione recensione = recensioni.get(position);

        nomeAutore.setText(recensione.getNomeAutore());
        ratingBar.setRating(recensione.getValue());

        return convertView;
    }
}
