package com.ui.innoguestapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ui.innoguestapplication.R;

import net.cachapa.expandablelayout.ExpandableLayout;

public class FAQList_adapter extends RecyclerView.Adapter<FAQList_adapter.ViewHolder> {

    private String[] questions, answers;
    RecyclerView rv;
    // boolean[] expanded;
    private static final int UNSELECTED = -1;
    private int selectedItem = UNSELECTED;


    @NonNull
    @Override
    public FAQList_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
        return new FAQList_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FAQList_adapter.ViewHolder holder, final int position) {

        holder.question.setText(questions[position]);
        holder.answer.setText(answers[position]);
        holder.bind();


//        holder.arrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               holder.layoutExpand.toggle();
//                notifyItemChanged(position);
//            }
//        });

    }


    public FAQList_adapter(String[] questions, String[] answers, RecyclerView rv) {
        this.questions = questions;
        this.answers = answers;
        this.rv = rv;
        // expanded = new boolean[questions.length];
        // Arrays.fill(expanded,false);
    }

    @Override
    public int getItemCount() {
        return questions.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {

        public TextView question;
        public TextView answer;
        public ImageButton arrow;
        ExpandableLayout layoutExpand;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            question = itemView.findViewById(R.id.faq_question);
            answer = itemView.findViewById(R.id.faq_answer);
            arrow = itemView.findViewById(R.id.faq_arrow);
            layoutExpand = itemView.findViewById(R.id.faq_expand);
            layoutExpand.setInterpolator(new OvershootInterpolator());
            layoutExpand.setOnExpansionUpdateListener(this);
            arrow.setOnClickListener(this);

        }

        public void bind() {

        }

        @Override
        public void onClick(View view) {
            ViewHolder holder = (ViewHolder) rv.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                holder.arrow.setSelected(false);
                holder.arrow.animate().setDuration(200).rotation(0);
                holder.layoutExpand.collapse();
            }

            int position = getAdapterPosition();
            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                arrow.setSelected(true);
                view.animate().setDuration(200).rotation(180);


                layoutExpand.expand();
                selectedItem = position;
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            //Log.d("ExpandableLayout", "State: " + state);
            if (state == ExpandableLayout.State.EXPANDING) {
                rv.smoothScrollToPosition(getAdapterPosition());
            }
        }
    }
}

