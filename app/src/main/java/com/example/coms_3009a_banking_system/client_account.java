package com.example.coms_3009a_banking_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class client_account extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Clientitem> mClientList;
    private ClientAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private Button AddButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_account);
        createClientList();
        buildRecyclerView();
        setButtons();

    }
    //////////////////////Function that adds Client items to the list and notify the adapter about the change////////////////////////////
    public void insertItem(int position){

        mClientList.add(new Clientitem("Savings Account","chosen account :" + position," ERR boy"));

        adapter.notifyItemInserted(position);
    }
    //////////////////////Function remove client items to the list/////////////////////////////////////////////////////////////////////////
    public void RemoveItem(int position){
        mClientList.remove(position);
        adapter.notifyItemRemoved(position);
    }
    //////////////Was to change client item but was not used///////////////////////////////////////////
    public void changeItem(int position ,String text){
        mClientList.get(position).changeText1(text);
        adapter.notifyItemChanged(position);
    }
    ////////////////////////////Making my cardview(which has three strings and an image)
    public void createClientList(){
        mClientList = new ArrayList<>();
        mClientList.add(new Clientitem("Account Type","Account Number", "Amount"));

    }
    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ClientAdapter(mClientList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new ClientAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
//                OpenActivity();
            }

            @Override
            public void onDeleteClick(int position) {
                RemoveItem(position);
            }
        });
    }

    public void setButtons(){
        AddButton = findViewById(R.id.button_insert);


        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                OpenActivity();
                // insertItem(0);//Inserting the item at the first position

            }
        });

    }

//    public void OpenActivity(){
//        Intent intent = new Intent(this,Banking.class);
//        startActivity(intent);
//    }


}