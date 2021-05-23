package com.example.coms_3009a_banking_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

public class AdminProfilePage extends AppCompatActivity {
    private  String email;
    private String password;

    private TextView FirstName;
    private TextView LastName;
    private EditText UserName;
    private EditText Email;
    private EditText P_Number;
    private static final String TAG = "Profile";
    private Button btnEditUsername,btnEditEmail, btnEditCell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile_page);

        FirstName= (TextView)findViewById(R.id.firstname);
        LastName= (TextView)findViewById(R.id.lastname);
        UserName=  (EditText)findViewById(R.id.username);
        Email= (EditText)findViewById(R.id.useremail);
        P_Number = (EditText)findViewById(R.id.p_number);

        btnEditUsername =(Button)findViewById(R.id.EditUsername);
        btnEditEmail =(Button)findViewById(R.id.EditEmail);
        btnEditCell =(Button)findViewById(R.id.EditCell);


        // get email and password via intent
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
                AdminProfilePage.this.runOnUiThread(new Runnable() {
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
                            Toast.makeText(AdminProfilePage.this, "Login  error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


//        Edit Username
        btnEditUsername.setOnClickListener(v -> {
            UserName.setEnabled(true);
            UserName.requestFocus();
        });

//        Edit Email
        btnEditEmail.setOnClickListener(v -> {
            Email.setEnabled(true);
            Email.requestFocus();
        });

//        Edit Cell

        btnEditCell.setOnClickListener(v -> {
            P_Number.setEnabled(true);
            P_Number.requestFocus();
        });


        // setting the navigation Menu

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);


        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.admin_profile);

        // Perform ItemSelected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.home:
                        intent = new Intent(getApplicationContext(),AdminVerification.class);
                        // add email and password on intent
                        intent.putExtra("email",email);
                        intent.putExtra("password",password);
                        startActivity(intent);
                        return true;

                    case R.id.pending_users:
                        intent = new Intent(getApplicationContext(),VerificationActivity.class);
                        //put email and password in intent
                        intent.putExtra("email",email);
                        intent.putExtra("password",password);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.verified_users:
                        intent = new Intent(getApplicationContext(),ClientsActivity.class);
                        //put email and password in intent
                        intent.putExtra("email",email);
                        intent.putExtra("password",password);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.admin_profile:
                        return true;

                }
                return false;
            }
        });


    }
}