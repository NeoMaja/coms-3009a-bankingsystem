package com.example.coms_3009a_banking_system.ClientAccount;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coms_3009a_banking_system.R;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {

    private ArrayList<Clientitem> mClientList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
        void onDeleteClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        public TextView mtext0;
        public TextView mtext1;
        public TextView mtext2;
        public ImageView deleteImage;

        public ClientViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            mtext0 = itemView.findViewById(R.id.account_type);
            mtext1 = itemView.findViewById(R.id.account_number);
            mtext2 = itemView.findViewById(R.id.amount);
//            deleteImage = itemView.findViewById(R.id.image_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }

                    //intent to account activity and pass account Number
                    //first capture account number

                    String AccNo = mtext1.getText().toString();
                    Intent intent = new Intent(itemView.getContext(), AccountActivity.class);
                    intent.putExtra("Account_Num", AccNo);
                    itemView.getContext().startActivity(intent);
                }
            });

//            deleteImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(listener != null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){
//                            listener.onDeleteClick(position);
//                        }
//                    }
//
//                }
//            });

        }
    }

    public ClientAdapter(ArrayList<Clientitem> clientitem){
        mClientList = clientitem;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false);
        ClientViewHolder cvh = new ClientViewHolder(v,mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        Clientitem currentItem = mClientList.get(position);

        holder.mtext0.setText(currentItem.getmText0());
        holder.mtext1.setText(currentItem.getmText1());
        holder.mtext2.setText(currentItem.getmText2());
    }

    @Override
    public int getItemCount() {
        return mClientList.size();
    }
}