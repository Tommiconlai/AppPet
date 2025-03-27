package com.example.apppet.animale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UnstableApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppet.Activities.CartellaClinicaActivity;
import com.example.apppet.Activities.HomeActivity;
import com.example.apppet.Activities.ProfiloAnimaleActivity;
import com.example.apppet.R;


import java.util.ArrayList;

public class ListaAnimaliAdapter extends RecyclerView.Adapter<ListaAnimaliAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Animale> animali;

    public ListaAnimaliAdapter(Context context, ArrayList<Animale> animali) { // Rimosso RecyclerViewInterface
        this.context = context;
        this.animali = animali;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_animali_image, parent, false);
        return new ViewHolder(view); // Rimosso RecyclerViewInterface
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animale animale = animali.get(position);
        holder.nomeAnimale.setText(animale.getNome());
        holder.ratingAnimale.setRating(animale.getRatingAnimale());
    }

    @Override
    public int getItemCount() {
        return animali.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder { // Rimosso "static"
        TextView nomeAnimale;
        RatingBar ratingAnimale;

        public ViewHolder(@NonNull View itemView) { // Rimosso RecyclerViewInterface
            super(itemView);
            nomeAnimale = itemView.findViewById(R.id.nome_animale);
            ratingAnimale = itemView.findViewById(R.id.rating_animale);

            itemView.setOnClickListener(new View.OnClickListener() {
                @OptIn(markerClass = UnstableApi.class)
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Animale selectedAnimal = animali.get(position);
                        long animalId = selectedAnimal.getId();
                        System.out.println("AnimalSelection" + "ID animale selezionato: " + animalId);

                        // Salva l'ID in SharedPreferences
                        SharedPreferences prefs = context.getSharedPreferences("animal_id_pref", Context.MODE_PRIVATE); // Assicurati che il nome sia coerente
                        prefs.edit().putLong("selected_animal_id", animalId).apply(); // Assicurati che la chiave sia coerente

                        System.out.println("AnimalSelection" + "ID animale salvato: " + animalId + " in SharedPreferences");

                        // Avvia CartellaClinicaActivity
                        Intent intent = new Intent(context, ProfiloAnimaleActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}