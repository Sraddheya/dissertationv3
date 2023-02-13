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

//public class OrganisationAdapter extends RecyclerView.Adapter<OrganisationAdapter.ViewHolder>{
//
//    Context context;
//    ArrayList<Organisation> organisations;
//    public OrganisationAdapter(Context context, ArrayList<Organisation> organisations) {
//        this.context = context;
//        this.organisations = organisations;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_organisation, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Organisation organisation = organisations.get(position);
//        holder.orgName.setText(organisation.getName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return organisations.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView orgName;
//        private RelativeLayout parent;
//
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            orgName = itemView.findViewById(R.id.orgName);
//            parent = itemView.findViewById(R.id.parent);
//        }
//    }
//}

public class OrganisationAdapter extends RecyclerView.Adapter<OrganisationAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Organisation> organisations = new ArrayList<>();

    public OrganisationAdapter(Context context){
        this.context = context;
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

    //USE notifyDataSetChanged() IF DATASET CHANGED WHEN PASSING FROM WEB SERVER
    public void setOrganisations(ArrayList<Organisation> organisations) {
        this.organisations = organisations;
        notifyDataSetChanged();
    }

    public interface RecyclerViewClickListener{
        void onCLick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView organisationName;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            organisationName = itemView.findViewById(R.id.OrganisationName);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
