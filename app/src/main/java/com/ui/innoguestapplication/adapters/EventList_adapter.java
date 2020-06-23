package com.ui.innoguestapplication.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ui.innoguestapplication.Event;
import com.ui.innoguestapplication.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

public class EventList_adapter extends RecyclerView.Adapter<EventList_adapter.ViewHolder> {

    ArrayList<Event> events;
    RecyclerView rv;

    private static final int UNSELECTED = -1;
    private int selectedItem = UNSELECTED;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new EventList_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event current_event = events.get(position);

        holder.name.setText(current_event.getEventName());
        holder.location.setText(current_event.getEventLocation());
        holder.time.setText(current_event.getEventTimeStart() + " - " + current_event.getEventTimeEnd());
        Log.d("list2", "holder reached");


    }

    public EventList_adapter(ArrayList<Event> events, RecyclerView rv) {
        this.events = events;
        this.rv = rv;
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
        public TextView time;
        public TextView name;
        public RatingBar rating;
        public TextView location;
        ExpandableLayout layoutExpand;
        ImageView arrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.schedule_time);
            name = itemView.findViewById(R.id.schedule_name);
            rating = itemView.findViewById(R.id.ratingBar);
            location = itemView.findViewById(R.id.schedule_location);
            layoutExpand = itemView.findViewById(R.id.schedule_expand);
            arrow = itemView.findViewById(R.id.schedule_arrow);
            layoutExpand.setInterpolator(new OvershootInterpolator());
            layoutExpand.setOnExpansionUpdateListener(this);
            itemView.setOnClickListener(this);
        }

        public void bind() {

        }

        @Override
        public void onClick(View view) {
            EventList_adapter.ViewHolder holder = (EventList_adapter.ViewHolder) rv.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                //holder.arrow.setSelected(false);
                holder.arrow.animate().setDuration(200).rotation(0);
                holder.layoutExpand.collapse();
            }

            int position = getAdapterPosition();
            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                //arrow.setSelected(true);
                arrow.animate().setDuration(200).rotation(180);


                layoutExpand.expand();
                selectedItem = position;
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            if (state == ExpandableLayout.State.EXPANDING) {
                rv.smoothScrollToPosition(getAdapterPosition());
            }
        }
    }
}




