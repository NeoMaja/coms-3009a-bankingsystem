package com.example.coms_3009a_banking_system.ClientAccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coms_3009a_banking_system.AsyncHTTPPost;
import com.example.coms_3009a_banking_system.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<historyItem> HistoryList;
    private historyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    //Activity to display the Account details, i.e Transaction history and Account Details.

    String Account_No;
    String Accn_type;
    String AccnBalance;


    //What will be displayed (Act_ID and Account_No2 Should not be displayed, but will be retrieved from php file)
    String Act_ID;
    String Account_No2;

    String Act;
    String To;
    String Amount;
    String Time;
    String Reference;

    //Do not display ID_No and Pin (But they'll be retrieved from php)
    String Account_No3;
    String Acc_Type;
    String ID_No;
    String Pin;
    String Balance;
    TextView Account_Number ,Account_Type , BalanceAmount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Get information using Intents
        Intent intent = getIntent();
        Account_No = intent.getStringExtra("Account_Num");
        Accn_type =intent.getStringExtra("Account Type");
        AccnBalance= intent.getStringExtra("amount");

    // Instantiate TextViews and recyclerview
        Account_Number = findViewById(R.id.account_numberV);
        Account_Type = findViewById(R.id.account_typeV);
        BalanceAmount = findViewById(R.id.balanceV);
        recyclerView = findViewById(R.id.transaction_list);


    // Set information on TextViews
        Account_Number.setText(Account_No);
        Account_Type.setText(Accn_type);
        BalanceAmount.setText(AccnBalance);

        ContentValues parameters = new ContentValues();
        parameters.put("Acc_No", Account_No);
        getAccounts(parameters);

        //https://lamp.ms.wits.ac.za/home/s2143686/Activity_list.php
        //php takes Account_No
        //gets the transaction history.
//        ContentValues parameters = new ContentValues();
//        parameters.put("Acc_No", Account_No);

//        OkHttpClient client = new OkHttpClient();
//
//        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2143686/Account_details.php").newBuilder(); //url here
//        urlBuilder.addQueryParameter("Acc_No",Account_No );
//
//        String url = urlBuilder.build().toString();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//
//            }
//
//        @Override
//        public void onResponse(@NotNull Call call, @NotNull Response response) throws
//        IOException {
//            final String responseData = response.body().string();
//            AccountActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        //This one must be put in a list
////                        JSONArray array = new JSONArray(responseData);
////                        for (int i = 0; i < array.length(); i++) {
////                            final JSONObject object = (JSONObject) array.get(i);
////                            Act_ID = object.getString("Activity_ID"); //No
////
////                            Account_No2 = object.getString("Account_Number");  //No
////                            Act = object.getString("Activity"); // Transaction Type
////                            To = object.getString("From_to");  // Account Sent to
////                            Amount = object.getString("Amount");
////                            Time = object.getString("Time");
//
//                        JSONObject object = new JSONObject(responseData);
//                        Account_No3 = object.getString("Account_Number");
//                        Toast.makeText(getApplicationContext(),Account_No3,Toast.LENGTH_SHORT).show();
//                        Acc_Type = object.getString("Account_Type_Name");
//
//                        ID_No = object.getString("ID_Number");
//                        Pin = object.getString("Pin");
//                        Balance = object.getString("Balance");
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//    });
//
////        AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/Activity_list.php",parameters){
////            @Override
////            protected void onPostExecute(String output) {
////                //json array to output transactions
////                try {
////                    JSONArray array = new JSONArray(output);
////                    for (int i = 0; i < array.length(); i++) {
////                        final JSONObject object = (JSONObject) array.get(i);
////                        Act_ID = object.getString("Activity_ID"); //No
////                        Account_No2 = object.getString("Account_Number");  //No
////                        Act = object.getString("Activity"); // Transaction Type
////                        To = object.getString("From_to");  // Account Sent to
////                        Amount = object.getString("Amount");
////                        Time = object.getString("Time");
////                    }
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////            }
////        };
////        asyncHttpPost.execute();

//        ContentValues parameters2 = new ContentValues();
//        parameters2.put("Acc_No", Account_No);
//        //gets Account details
////
//        AsyncHTTPPost asyncHttpPost2 = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/Account_details.php",parameters2){
//            @Override
//            protected void onPostExecute(String output) {
//                //json array to output transactions
//                try {
//                        JSONObject object = new JSONObject(output);
//                        Account_No3 = object.getString("Account_Number");
//                        Acc_Type = object.getString("Account_Type_Name");
//                        Account_Type.setText(Acc_Type);
//                        ID_No = object.getString("ID_Number");
//                        Pin = object.getString("Pin");
//                        Balance = object.getString("Balance");
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        asyncHttpPost2.execute();


        //TODO
        //display information from php files


//        Account_Type.setText("Savings");


//        Log.e("Activity Type" ,Acc_Type);
//        BalanceAmount.setText(Balance);
//        Log.e("Activity Balance" ,Balance);

        // create Transaction Adapter and create a list of transactions


    }

    private void getAccounts(ContentValues parameters){
        recyclerView = findViewById(R.id.transaction_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/Activity_list.php", parameters){
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray array = new JSONArray(output);
                    ArrayList<historyItem> historyItems = new ArrayList<>();
                    historyItem historyItem;
                    for(int i =0; i < array.length(); i++){
                        final JSONObject object = (JSONObject) array.get(i);
                        Act_ID = object.getString("Activity_ID"); //No
                        Account_No2 = object.getString("Account_Number");  //No
                        Act = object.getString("Activity"); // Transaction Type
                        To = object.getString("reciever");  // Account Sent to
                        Amount = object.getString("Amount");
                        Reference = object.getString("Reference");
                        Time = object.getString("Time");

                        historyItem = new historyItem(Reference, Act, Amount, Time);
                        historyItems.add(historyItem);
                        HistoryList = historyItems;
                    }

                    if (HistoryList.size() == 0){
                        Toast toast = Toast.makeText(AccountActivity.this, "This Account has No Historical Transactions", Toast.LENGTH_LONG);
                    }else{
                        adapter = new historyAdapter(HistoryList);
                        recyclerView.setAdapter(adapter);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }.execute();
    }
}