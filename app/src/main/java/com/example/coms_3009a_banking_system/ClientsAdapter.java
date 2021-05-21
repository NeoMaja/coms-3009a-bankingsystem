package com.example.coms_3009a_banking_system;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.myViewHolder> {

    private Context mCt;
    private List<ClientUser> clientList;

    public ClientsAdapter(Context mCt, List<ClientUser> clientList) {
        this.mCt = mCt;
        this.clientList = clientList;

    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView mName;
        TextView mID;
        TextView mEmail;
        TextView mPhone;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.title1);
            mID = itemView.findViewById(R.id.id_subtitle1);
            mEmail = itemView.findViewById(R.id.email_subtitle1);
            mPhone = itemView.findViewById(R.id.no_subtitle1);

        }
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflates cardview
        LayoutInflater inflater = LayoutInflater.from(mCt);
        View view = inflater.inflate(R.layout.acceptuser,null);
        //Returns view to Holder class
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ClientUser user = clientList.get(position);

        holder.mName.setText(user.getName());
        holder.mID.setText(user.getId());
        holder.mEmail.setText(user.getEmail());
        holder.mPhone.setText(user.getPhoneNo());

    }


    public void Delete(int position)
    {
        clientList.remove(position);
    }
    @Override
    public int getItemCount() {
        return clientList.size();
    }


}
