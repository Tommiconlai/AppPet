package com.example.apppet.servizio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppet.R;
import com.example.apppet.Activities.RecyclerViewInterface;

import java.util.ArrayList;

public class ServizioAdapter extends RecyclerView.Adapter<ServizioAdapter.ViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private ArrayList<Servizio> servizi;

    public ServizioAdapter(Context context, ArrayList<Servizio> servizi, RecyclerViewInterface recyclerViewInterface) {
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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNome;
        TextView tvDescr;

        TextView tvIndirizzo;

        TextView tvFornitore;

        TextView tvOrario;


        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tvNome = itemView.findViewById(R.id.tvNomeAttivita);
            tvDescr = itemView.findViewById(R.id.tvDescrizione);
            tvFornitore = itemView.findViewById(R.id.tvFornitore);
            tvIndirizzo = itemView.findViewById(R.id.tvIndirizzo);
            tvOrario = itemView.findViewById(R.id.tvOrario);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClicked(position);
                        }
                    }
                }
            });
        }

    }


}


