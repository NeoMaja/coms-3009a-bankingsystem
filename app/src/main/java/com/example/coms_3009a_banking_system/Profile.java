package com.example.coms_3009a_banking_system;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
    String lastname; String username;



    private TextView FirstName;
    private TextView LastName;
    private TextView UserName;
    private TextView Email;
    private TextView P_Number;
    private static final String TAG = "Profile";
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
        Log.e(TAG,email);
        Log.e(TAG,password);




        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2143686/retrieveUserData.php").newBuilder(); //url here
        urlBuilder.addQueryParameter("email", email);
        urlBuilder.addQueryParameter("password", password);
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
        }



    }


