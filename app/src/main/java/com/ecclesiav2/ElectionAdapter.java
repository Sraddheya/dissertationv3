package com.ecclesiav2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        private TextView electionTitle;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            electionTitle = itemView.findViewById(R.id.electionTitle);
            parent = itemView.findViewById(R.id.parent);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onCLick(view, getAdapterPosition());
        }
    }

}
