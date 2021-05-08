package com.example.coms_3009a_banking_system.Registration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coms_3009a_banking_system.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
     Context c;
     List<Model> models = new ArrayList<>();

    public Adapter(Context c, List<Model> models) {
        this.c = c;
        this.models = models;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

         TextView mName;
         TextView mID;
         TextView mEmail;
         TextView mPhone;
         ImageView mImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.title);
            mID = itemView.findViewById(R.id.id_subtitle);
            mEmail = itemView.findViewById(R.id.email_subtitle);
            mPhone = itemView.findViewById(R.id.no_subtitle);
            mImageView = itemView.findViewById(R.id.icon_Person);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflates cardview
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        //Returns view to Holder class
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Model model = models.get(position);

        Glide.with(c).load(models.get(position)).into(holder.mImageView);
        holder.mName.setText(models.get(position).getName());
        holder.mID.setText(models.get(position).getId());
        holder.mEmail.setText(models.get(position).getEmail());
        holder.mPhone.setText(models.get(position).getPhoneNo());

    }


    @Override
    public int getItemCount() {
        return models.size();
    }
}
