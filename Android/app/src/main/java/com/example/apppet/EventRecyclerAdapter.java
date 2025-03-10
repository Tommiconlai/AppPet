package com.example.apppet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<Events> arrayList;

    DBOpenHelper dbOpenHelper;

    CustomCalendarView calendarView;

    public EventRecyclerAdapter(Context context, ArrayList<Events> arrayList, CustomCalendarView calendarView) {
        this.context = context;
        this.arrayList = arrayList;
        this.calendarView = calendarView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_event_raw_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Events events = arrayList.get(position);
        holder.Event.setText(events.getEVENT());
        holder.DateTxt.setText(events.getDATE());
        holder.Time.setText(events.getTIME());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                deleteCalendarEvent(events.getEVENT(), events.getDATE(), events.getTIME());
                arrayList.remove(position);
                notifyDataSetChanged();
                if (calendarView != null) {
                    calendarView.updateCalendar();
                }

            }
        });
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView DateTxt, Event, Time;
        ImageButton delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            DateTxt = itemView.findViewById(R.id.eventdate);
            Event = itemView.findViewById(R.id.eventname);
            Time = itemView.findViewById(R.id.eventtime);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    private void deleteCalendarEvent(String event, String date, String time) {
        try {
            dbOpenHelper = new DBOpenHelper(context);
            SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
            dbOpenHelper.deleteEvent(event, date, time, database);
            dbOpenHelper.close();

            Toast.makeText(context, "Evento cancellato", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(context, "Errore durante la cancellazione dell'evento", Toast.LENGTH_SHORT).show();
        }
    }

}
