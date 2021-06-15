package com.example.coms_3009a_banking_system.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coms_3009a_banking_system.AdminVerification;
import com.example.coms_3009a_banking_system.AdminWelcomePage;
import com.example.coms_3009a_banking_system.AsyncHTTPPost;
import com.example.coms_3009a_banking_system.Client_Verification_Status;
import com.example.coms_3009a_banking_system.R;
import com.example.coms_3009a_banking_system.Registration.AdminKeyView;
import com.example.coms_3009a_banking_system.Registration.adminRegister;

public class  AdminLoginKey extends AppCompatActivity {

    Button Enter;
    EditText Admin_key;
    String Key;

    String Email;
    String Password;
   // String Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_key);

        Email = getIntent().getStringExtra("email");
        Password = getIntent().getStringExtra("password");
     //   Status= getIntent().getStringExtra("status");

        Enter = (Button)findViewById(R.id.enter_key_log);
        Admin_key = (EditText)findViewById(R.id.adminlog_key);

        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the strings
                Key = Admin_key.getText().toString();

                //comparing Admin keys from the database
                ContentValues parameters = new ContentValues();
                parameters.put("email", Email);
                parameters.put("key", Key);

                AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/getAdminKey.php",parameters){
                    @Override
                    protected void onPostExecute(String output) {
                        //if output = success then go the activity page
                        if(output.equals("success")){
                            Intent intent = new Intent(AdminLoginKey.this, AdminWelcomePage.class);
                            intent.putExtra("email",Email);
                            intent.putExtra("password",Password);
                          //  intent.putExtra("status",Status);
                            startActivity(intent);
                            finish();
                        }
                        if(output.equals("fail")){
                            Toast.makeText(getApplicationContext(), "Incorrect key", Toast.LENGTH_SHORT).show();
                        }

                    }
                };
                asyncHttpPost.execute();

            }
        });
    }
}