package com.ui.innoguestapplication.adapters;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.events.Event;
import com.ui.innoguestapplication.fragments.ScheduleListFragment;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

public class EventList_adapter extends RecyclerView.Adapter<EventList_adapter.ViewHolder> {

    ArrayList<Event> events;
    RecyclerView rv;
    ScheduleListFragment parent_fragment;

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
        holder.description.setText(current_event.getEventName());


        //API
        //holder.toggleButton.setChecked();


        int pL = holder.background.getPaddingLeft();
        int pT = holder.background.getPaddingTop();
        int pR = holder.background.getPaddingRight();
        int pB = holder.background.getPaddingBottom();

        Drawable red_background = ContextCompat.getDrawable(parent_fragment.getActivity(), R.drawable.schedulebg_red);
        //if (event.obligatory){
        if (position % 2 == 0) {
            holder.background.setBackground(red_background);
            holder.background.setPadding(pL, pT, pR, pB);
        }


    }

    public EventList_adapter(ArrayList<Event> events, RecyclerView rv, ScheduleListFragment fragment) {
        this.events = events;
        this.rv = rv;
        this.parent_fragment = fragment;
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
        public TextView time;
        public TextView name;
        public TextView description;
        public TextView location;
        ExpandableLayout layoutExpand;
        ImageView arrow;
        ConstraintLayout background;
        ToggleButton toggleButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.schedule_time);
            name = itemView.findViewById(R.id.schedule_name);
            description = itemView.findViewById(R.id.description_text);
            location = itemView.findViewById(R.id.schedule_location);
            layoutExpand = itemView.findViewById(R.id.schedule_expand);
            arrow = itemView.findViewById(R.id.schedule_arrow);
            layoutExpand.setInterpolator(new OvershootInterpolator());
            layoutExpand.setOnExpansionUpdateListener(this);
            itemView.setOnClickListener(this);
            background = itemView.findViewById(R.id.schedule_item_bg);
            toggleButton = itemView.findViewById(R.id.toggle_event);
            int width = ((int) toggleButton.getPaint().measureText(parent_fragment.getString(R.string.not_coming)) + toggleButton.getPaddingLeft() + toggleButton.getPaddingRight());
            toggleButton.setWidth(width + 80);

            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        //API
                    } else {
                        //API
                    }
                }
            });
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




