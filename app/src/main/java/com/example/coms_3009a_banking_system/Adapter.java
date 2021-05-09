package com.example.coms_3009a_banking_system;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
     private Context mCtx;
     private List<User> userList;

    public Adapter(Context mCtx, List<User> userList) {
        this.mCtx = mCtx;
        this.userList = userList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

         TextView mName;
         TextView mID;
         TextView mEmail;
         TextView mPhone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.title);
            mID = itemView.findViewById(R.id.id_subtitle);
            mEmail = itemView.findViewById(R.id.email_subtitle);
            mPhone = itemView.findViewById(R.id.no_subtitle);

        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflates cardview
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.user,null);
        //Returns view to Holder class
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = userList.get(position);

        holder.mName.setText(user.getName());
        holder.mID.setText(user.getId());
        holder.mEmail.setText(user.getEmail());
        holder.mPhone.setText(user.getPhoneNo());

    }


    @Override
    public int getItemCount() {
        return userList.size();
    }
}
