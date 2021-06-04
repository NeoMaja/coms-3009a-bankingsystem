package com.example.coms_3009a_banking_system;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coms_3009a_banking_system.ClientAccount.Cli_Acc_Test;
import com.example.coms_3009a_banking_system.ClientAccount.ClientAdapter;
import com.example.coms_3009a_banking_system.ClientAccount.Clientitem;
import com.example.coms_3009a_banking_system.ClientAccount.Pay;
import com.example.coms_3009a_banking_system.ClientAccount.Transfer;
import com.example.coms_3009a_banking_system.ClientAccount.client_account;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

public class Profile extends AppCompatActivity {

    private  String email;
    private String password;

    String firstname;
    String lastname;
    String username;
    String cellphone;



    private TextView FirstName;
    private TextView LastName;
    private TextView UserName;
    private TextView Email;
    private TextView P_Number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        FirstName= (TextView)findViewById(R.id.firstname);
        LastName= (TextView)findViewById(R.id.lastname);
        UserName=  (TextView)findViewById(R.id.username);
        Email= (TextView)findViewById(R.id.useremail);
        P_Number = (TextView)findViewById(R.id.p_number);



        Intent getIntent= getIntent();
        email = getIntent.getStringExtra("email");
        password = getIntent.getStringExtra("password");

        System.out.println("email  :"+email);
        System.out.println("Password  :"+password);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


//    //Parameters
//        ContentValues parameters = new ContentValues();
//        parameters.put("email", email );
////        parameters.put("password", password);
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2143686/retrieveUserData.php").newBuilder(); //url here
        urlBuilder.addQueryParameter("email", email);
//        urlBuilder.addQueryParameter("password", password);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {


            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseData = response.body().string();
                Profile.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jO = new JSONObject(responseData);
                            String first_name = jO.getString("First Name");
                            String last_name = jO.getString("Last Name");
                            String user_name =jO.getString("username");
                            String  _email = jO.getString("email");
                            String number = jO.getString("cellphone");
                            FirstName.setText(first_name);
                            UserName.setText(user_name.toUpperCase());
                            LastName.setText(last_name);
                            Email.setText(_email);
                            P_Number.setText(number);

                            ;
                            //  Toast.makeText(Profile.this, first_name, Toast.LENGTH_SHORT).show();
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(Profile.this, "Login  error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });




        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.button_profile);

        // Perform ItemSelected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent1;
                switch (item.getItemId()){

                    case R.id.button_profile:
                        return true;

                    case R.id.acc:
                        intent1 = new Intent(Profile.this, client_account.class);
                        intent1.putExtra("email",email);
                        intent1.putExtra("password",password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_transact:
                        intent1 = new Intent(Profile.this, Transfer.class);
                        intent1.putExtra("email",email);
                        intent1.putExtra("password",password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_insert:
                        intent1 = new Intent(Profile.this, Cli_Acc_Test.class);
                        intent1.putExtra("email",email);
                        intent1.putExtra("password",password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.button_pay:
                        intent1 = new Intent(Profile.this, Pay.class);
                        intent1.putExtra("email",email);
                        intent1.putExtra("password",password);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });
        }



    }


