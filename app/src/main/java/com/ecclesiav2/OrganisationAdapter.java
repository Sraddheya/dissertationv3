package com.ecclesiav2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrganisationAdapter extends RecyclerView.Adapter<OrganisationAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Organisation> organisations = new ArrayList<>();
    private RecyclerViewClickListener listener;
    public OrganisationAdapter(Context context, RecyclerViewClickListener listener){
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_organisation, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Organisation organisation = organisations.get(position);
        holder.organisationName.setText(organisation.getName());
    }

    @Override
    public int getItemCount() {
        return organisations.size();
    }

    public void setOrganisations(ArrayList<Organisation> organisations) {
        this.organisations = organisations;
        notifyDataSetChanged();//Get any data updates
    }

    public interface RecyclerViewClickListener{
        void onCLick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView organisationName;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            organisationName = itemView.findViewById(R.id.OrganisationName);
            parent = itemView.findViewById(R.id.parent);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onCLick(view, getAdapterPosition());
        }
    }
}
