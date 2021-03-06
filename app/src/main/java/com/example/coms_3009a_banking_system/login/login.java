package com.example.coms_3009a_banking_system.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.coms_3009a_banking_system.MainActivity;
import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.usertype;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class login extends AppCompatActivity {

    private TextInputEditText Email, Password;
    private Button Login, NewAcc;

    //////////////for the pop up////////////
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (TextInputEditText)findViewById(R.id.username_l);
        Password = (TextInputEditText)findViewById(R.id.password_l);
        Login = (Button) findViewById(R.id.login_id);
        NewAcc = (Button) findViewById(R.id.new_acc_id);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidateLogin();


              ////////the line below must be used if a client whose an admin and client tries to login.
                //dialog.show();//
            }
        });

        NewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userTypeIntent = new Intent(login.this, usertype.class);
                startActivity(userTypeIntent);
            }

        });
//////////////////////Creation of the popup_screen/dialog//////////////////////////////////////////
        /*
        dialog = new Dialog(Login.getContext());
        dialog.setContentView(R.layout.popup_screen);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_popup));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        Button admin = dialog.findViewById(R.id.admin_button);
        Button client = dialog.findViewById(R.id.client_btn);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.getContext(), "You chose to Login as an admin",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.getContext(), "You chose to Login as an admin",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

         */

    }

    private void ValidateLogin() {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        if (email.isEmpty()){
            Email.setError("Enter email address");
            Email.requestFocus();
        }
        else if(email.length()<10){
            Email.setError("Enter valid email address");
            Email.requestFocus();
        }
        else if (password.isEmpty()){
            Password.setError("Enter password");
            Password.requestFocus();
        }else if(password.length()<5){
            Password.setError("Password must be 5 characters or more");
            Password.requestFocus();
        }
        else{
            doLogin(email, password);
            if(isNetworkStatusAvailable (getApplicationContext())) {
                Toast.makeText(getApplicationContext(), "internet available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "internet is not available", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private static boolean isNetworkStatusAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            @SuppressLint("MissingPermission") NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

    private void doLogin(String email, final String password) {
        OkHttpClient client = new OkHttpClient();
////////////////////////DATABASE/////////////////////////////////////////////////////////////////////
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2143686/userLogin.php").newBuilder();//url here
        urlBuilder.addQueryParameter("email", email);
        urlBuilder.addQueryParameter("password", password);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseData = response.body().string();
                login.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jO = new JSONObject(responseData);
                            String success = jO.getString("success");

                            if(success.equals("250")){ //client login, should take you to homepage
                                Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Email.requestFocus();
                                Password.requestFocus();
                                //Login path

                                Intent loginIntent = new Intent(login.this, MainActivity.class);///LOGIN BUTTON HERE
                                loginIntent.putExtra("email",Email.getText().toString().trim());
                                startActivity(loginIntent);
                                login.this.finish();
                            }
                            if(success.equals("300")){ //admin login
                                Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Email.requestFocus();
                                Password.requestFocus();
                                Intent loginIntent = new Intent(login.this,MainActivity.class);///LOGIN BUTTON HERE //needs to be edited so it takes you to verification screen
                                loginIntent.putExtra("email",Email.getText().toString().trim());
                                startActivity(loginIntent);
                                login.this.finish();
                            }
                            if(success.equals("0")) {
                                Toast.makeText(login.this, "Email not Registered", Toast.LENGTH_SHORT).show();
                                Email.setError("Enter registered email");
                                Email.requestFocus();
                                Password.requestFocus();
                            }
                            if(success.equals("-1")) {
                                Toast.makeText(login.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                                Password.setError("Enter correct password");
                                Password.requestFocus();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(login.this, "Login  error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call, final IOException e) {
                e.printStackTrace();

            }
        });
    }

}
