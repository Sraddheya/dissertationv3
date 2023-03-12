package com.ecclesiav2;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ElectionAdapter extends RecyclerView.Adapter<ElectionAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Election> elections = new ArrayList<>();
    private ElectionAdapter.RecyclerViewClickListener listener;

    public ElectionAdapter(Context context, ElectionAdapter.RecyclerViewClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_election, parent, false);
        ElectionAdapter.ViewHolder holder = new ElectionAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Election election = elections.get(position);
        holder.electionTitle.setText(election.getTitle());
        holder.startTime.setText("Voting starts at: " + election.getStartCast());
        holder.endTime.setText("Voting ends at: " + election.getEndCast());

        //Set tags
        if (election.getStatus().equals("Joined")){
            holder.waitingTag.setVisibility(View.VISIBLE);
        } else if (election.getStatus().equals("Results calculated")){
            holder.closedTag.setVisibility(View.VISIBLE);
        } else {
            holder.activeTag.setVisibility(View.VISIBLE);
        }

        if (election.getSelectedIndex() > 0){
            holder.votedTag.setVisibility(View.VISIBLE);
            if (election.getNeedReCast() == 1){
                holder.card_layout.setCardBackgroundColor(Color.parseColor("#E05A47"));
            }
            if (election.getNeedReCast() == 0){
                holder.card_layout.setCardBackgroundColor(Color.parseColor("#7DA0FA"));
            }
            Log.d("color", "hello");
        }
    }

    @Override
    public int getItemCount() {
        return elections.size();
    }

    public void setElections(ArrayList<Election> elections) {
        this.elections = elections;
        notifyDataSetChanged();//Get any data updates
    }

    public interface RecyclerViewClickListener{
        void onCLick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView electionTitle, startTime, endTime;
        private CardView card_layout, waitingTag, votedTag, activeTag, closedTag;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card_layout = itemView.findViewById(R.id.card_layout);

            electionTitle = itemView.findViewById(R.id.electionTitle);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);
            parent = itemView.findViewById(R.id.parent);

            waitingTag = itemView.findViewById(R.id.waitingTag);
            votedTag = itemView.findViewById(R.id.votedTag);
            activeTag = itemView.findViewById(R.id.activeTag);
            closedTag = itemView.findViewById(R.id.closedTag);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onCLick(view, getAdapterPosition());
        }
    }

}
