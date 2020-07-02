package com.ui.innoguestapplication.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.sqlite_database.Notification;

import java.util.ArrayList;

public class Notifications_adapter extends RecyclerView.Adapter<Notifications_adapter.ViewHolder> {

    ArrayList<Notification> data;

    public Notifications_adapter(ArrayList<Notification> data) {
        this.data = data;
        //Log.d("notifications", "constructor works");

    }

    public void addNotification(Notification newNotification){
        data.add(newNotification);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new Notifications_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notifications_adapter.ViewHolder holder, int position) {
        //Log.d("notifications", data.get(position).getText());

        holder.text.setText(data.get(position).getText());
        holder.date_time.setText(data.get(position).getDate_or_time());
        holder.bind();

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        TextView date_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.notification_text);
            date_time = itemView.findViewById(R.id.notification_time);
        }

        public void bind() {

        }
    }
}
