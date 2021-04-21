package com.example.coms_3009a_banking_system;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class register extends AppCompatActivity {

    private TextInputEditText Username,Email,Password,cPassword;
    private RadioGroup Gender;
    private ProgressBar progressbar;
    private Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username =(TextInputEditText) findViewById(R.id.username);
        Email =(TextInputEditText) findViewById(R.id.email);
        Password =(TextInputEditText) findViewById(R.id.password);
        cPassword =(TextInputEditText) findViewById(R.id.cpassword);
        Gender = (RadioGroup) findViewById(R.id.radiosex);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        btnDisplay = (Button) findViewById(R.id.create_acc);
        progressbar.setVisibility(View.GONE);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDataAndDoRegister();

            }
        });

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

    private void validateDataAndDoRegister() {
        String username = Username.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String c_password = cPassword.getText().toString().trim();
        String gender = null;
        String assistance = null;

        if(Gender.getCheckedRadioButtonId()!=-1){
            int id= Gender.getCheckedRadioButtonId();
            View radioButton = Gender.findViewById(id);
            int radioId = Gender.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) Gender.getChildAt(radioId);
            gender = btn.getText().toString().trim();

        }

        if (username.isEmpty()) {
            Username.setError("enter surname address");
            Username.requestFocus();
        }
        else if(username.length()<5){
            Username.setError("username too short");
            Username.requestFocus();
        }
        else if (email.isEmpty()){
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
        else if (c_password.isEmpty()) {
            cPassword.setError("enter password");
            cPassword.requestFocus();
        }
        else if (!password.equals(c_password)){
            Password.setError("passwords do not match");
            Password.requestFocus();
            cPassword.setError("passwords do not match");
            cPassword.requestFocus();
            Password.setText("");
            cPassword.setText("");
        }
        else{
            doRegister(username,email,password,gender,assistance);
            progressbar.setVisibility(View.VISIBLE);
            if(isNetworkStatusAvialable (getApplicationContext())) {
                Toast.makeText(getApplicationContext(), "internet avialable", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "internet is not avialable", Toast.LENGTH_SHORT).show();
                progressbar.setVisibility(View.GONE);
            }
        }
    }


    private void doRegister(String username, String email, String password, String gender, String assistance) {
        OkHttpClient client = new OkHttpClient();
///////////////////////////////meng's DATABASE///////////////////////////////////////////////////////////////////////////
        HttpUrl.Builder urlBuilder = HttpUrl.parse("").newBuilder();
        urlBuilder.addQueryParameter("username", username);
        urlBuilder.addQueryParameter("email", email);
        urlBuilder.addQueryParameter("password", password);
        urlBuilder.addQueryParameter("gender", gender);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseData = response.body().string();
                register.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jO = new JSONObject(responseData);
                            String success = jO.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(register.this, "Register Success", Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.GONE);
                                Intent loginIntent = new Intent(register.this,MainActivity.class);
                                startActivity(loginIntent);
                                register.this.finish();
                            }
                            if(success.equals("-1")) {
                                Toast.makeText(register.this, "Email Registered", Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.GONE);
                            }
                            if(success.equals("2")) {
                                Toast.makeText(register.this, "Username registered", Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(register.this, "Register Error", Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(View.GONE);
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
