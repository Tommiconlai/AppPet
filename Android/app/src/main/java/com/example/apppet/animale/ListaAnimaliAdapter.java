package com.example.apppet.animale;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppet.Activities.ProfiloAnimaleActivity;
import com.example.apppet.Activities.RecyclerViewInterface;
import com.example.apppet.R;

import java.util.ArrayList;

public class ListaAnimaliAdapter extends RecyclerView.Adapter<ListaAnimaliAdapter.ViewHolder> {
    private final RecyclerViewInterface recyclerViewListaAnimaliInterface;
    private Context context;
    private ArrayList<Animale> animali;

    public ListaAnimaliAdapter(Context context, ArrayList<Animale> animali, RecyclerViewInterface recyclerViewListaAnimaliInterface) {
        this.context = context;
        this.animali = animali;
        this.recyclerViewListaAnimaliInterface = recyclerViewListaAnimaliInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_animali_image, parent, false);
        return new ViewHolder(view, recyclerViewListaAnimaliInterface);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomeAnimale;
        RatingBar ratingAnimale;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewListaAnimaliInterface) {
            super(itemView);
            nomeAnimale = itemView.findViewById(R.id.nome_animale);
            ratingAnimale = itemView.findViewById(R.id.rating_animale);

            ratingAnimale.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if (recyclerViewListaAnimaliInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewListaAnimaliInterface.onRatingChanged(position, rating);
                        }

                    }

                }
            });

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