package com.example.coms_3009a_banking_system.ClientAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.media.audiofx.AcousticEchoCanceler;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coms_3009a_banking_system.AsyncHTTPPost;
import com.example.coms_3009a_banking_system.Profile;
import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.Registration.clientRegister;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AccountActivity extends AppCompatActivity {

    //Activity to display the Account details, i.e Transaction history and Account Details.

    String Account_No;

    //What will be displayed (Act_ID and Account_No2 Should not be displayed, but will be retrieved from php file)
    String Act_ID;
    String Account_No2;

    String Act;
    String To;
    String Amount;
    String Time;

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

        Intent intent = getIntent();
        Account_No = intent.getStringExtra("Account_Num");
        Toast.makeText(getApplicationContext(),Account_No,Toast.LENGTH_SHORT).show();


        Account_Number = findViewById(R.id.account_numberV);
        Account_Type = findViewById(R.id.account_typeV);
        BalanceAmount = findViewById(R.id.balanceV);

        //https://lamp.ms.wits.ac.za/home/s2143686/Activity_list.php
        //php takes Account_No
        //gets the transaction history.
//        ContentValues parameters = new ContentValues();
//        parameters.put("Acc_No", Account_No);

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2143686/Account_details.php").newBuilder(); //url here
        urlBuilder.addQueryParameter("Acc_No",Account_No );

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {


            }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws
        IOException {
            final String responseData = response.body().string();
            AccountActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //This one must be put in a list
//                        JSONArray array = new JSONArray(responseData);
//                        for (int i = 0; i < array.length(); i++) {
//                            final JSONObject object = (JSONObject) array.get(i);
//                            Act_ID = object.getString("Activity_ID"); //No
//
//                            Account_No2 = object.getString("Account_Number");  //No
//                            Act = object.getString("Activity"); // Transaction Type
//                            To = object.getString("From_to");  // Account Sent to
//                            Amount = object.getString("Amount");
//                            Time = object.getString("Time");

                        JSONObject object = new JSONObject(responseData);
                        Account_No3 = object.getString("Account_Number");
                        Acc_Type = object.getString("Account_Type_Name");
                        Account_Type.setText("Savings");
                        ID_No = object.getString("ID_Number");
                        Pin = object.getString("Pin");
                        Balance = object.getString("Balance");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    });

//        AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/Activity_list.php",parameters){
//            @Override
//            protected void onPostExecute(String output) {
//                //json array to output transactions
//                try {
//                    JSONArray array = new JSONArray(output);
//                    for (int i = 0; i < array.length(); i++) {
//                        final JSONObject object = (JSONObject) array.get(i);
//                        Act_ID = object.getString("Activity_ID"); //No
//                        Account_No2 = object.getString("Account_Number");  //No
//                        Act = object.getString("Activity"); // Transaction Type
//                        To = object.getString("From_to");  // Account Sent to
//                        Amount = object.getString("Amount");
//                        Time = object.getString("Time");
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        asyncHttpPost.execute();

        ContentValues parameters2 = new ContentValues();
        parameters2.put("Acc_No", Account_No);
        //gets Account details
//
        AsyncHTTPPost asyncHttpPost2 = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/Account_details.php",parameters2){
            @Override
            protected void onPostExecute(String output) {
                //json array to output transactions
                try {
                        JSONObject object = new JSONObject(output);
                        Account_No3 = object.getString("Account_Number");
                        Acc_Type = object.getString("Account_Type_Name");
                        Account_Type.setText(Acc_Type);
                        ID_No = object.getString("ID_Number");
                        Pin = object.getString("Pin");
                        Balance = object.getString("Balance");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        asyncHttpPost2.execute();


        //TODO
        //display information from php files

        Account_Number.setText(Account_No);

        Account_Type.setText("Savings");


//        Log.e("Activity Type" ,Acc_Type);
//        BalanceAmount.setText(Balance);
//        Log.e("Activity Balance" ,Balance);

    }
}