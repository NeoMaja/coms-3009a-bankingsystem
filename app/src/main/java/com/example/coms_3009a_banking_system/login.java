package com.example.coms_3009a_banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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
            }
        });

        NewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userTypeIntent = new Intent(login.this,usertype.class);
                startActivity(userTypeIntent);
            }

        });

    }

    private void ValidateLogin() {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        if (email.isEmpty()){
            Email.setError("enter email address");
            Email.requestFocus();
        }
        else if(email.length()<10){
            Email.setError("enter valid email address");
            Email.requestFocus();
        }
        else if (password.isEmpty()){
            Password.setError("enter password");
            Password.requestFocus();
        }else if(password.length()<8){
            Password.setError("password must be 8 characters or more");
            Password.requestFocus();
        }
        else{
            doLogin(email, password);
            if(isNetworkStatusAvialable (getApplicationContext())) {
                Toast.makeText(getApplicationContext(), "internet avialable", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "internet is not avialable", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

    private void doLogin(String email, final String password) {
        OkHttpClient client = new OkHttpClient();
////////////////////////DATABASE/////////////////////////////////////////////////////////////////////
        HttpUrl.Builder urlBuilder = HttpUrl.parse("").newBuilder();//url here
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
                            if(success.equals("1")){
                                Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Email.requestFocus();
                                Password.requestFocus();
                                Intent loginIntent = new Intent(login.this,MainActivity.class);///LOGIN BUTTON HERE
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
                            Toast.makeText(login.this, "Login error", Toast.LENGTH_SHORT).show();
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
