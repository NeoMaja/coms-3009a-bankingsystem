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

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.HistoryViewHolder> {

    private ArrayList<historyItem> historyItems;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
        void onDeleteClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        public TextView ref;
        public TextView tr_type;
        public TextView amount_tr;
        public TextView tr_date;

        public HistoryViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            ref = itemView.findViewById(R.id.trans_ref);
            tr_type = itemView.findViewById(R.id.Trans_type);
            amount_tr = itemView.findViewById(R.id.trans_amount);
            tr_date = itemView.findViewById(R.id.trans_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public historyAdapter(ArrayList<historyItem> historyItem){
        historyItems = historyItem;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trans_history_item, parent, false);
        HistoryViewHolder cvh = new HistoryViewHolder(v,mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        historyItem currentItem = historyItems.get(position);

        holder.ref.setText(currentItem.getReference());
        holder.tr_type.setText(currentItem.getTransaction_type());
        holder.amount_tr.setText(currentItem.getAmount());
        holder.tr_date.setText(currentItem.getDate());
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }
}
