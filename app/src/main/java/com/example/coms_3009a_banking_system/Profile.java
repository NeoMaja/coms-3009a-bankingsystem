package com.example.coms_3009a_banking_system;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coms_3009a_banking_system.ClientAccount.client_account;
import com.example.coms_3009a_banking_system.login.AdminLoginKey;
import com.example.coms_3009a_banking_system.login.Login2;

import org.w3c.dom.Text;

import java.util.logging.Logger;

public class Profile extends AppCompatActivity {

    private  String email;
    private String password;

    String firstname;
    String lastname; String username;



    private TextView firstName;
    private TextView LastName;
    private TextView UserName;
    private TextView Email;
    private TextView Password;
    private static final String TAG = "Profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

       firstName= (TextView)findViewById(R.id.firstname);
        LastName= (TextView)findViewById(R.id.lastname);
        UserName=  (TextView)findViewById(R.id.username);
        Email= (TextView)findViewById(R.id.useremail);
        Password= (TextView)findViewById(R.id.password);



        Intent getIntent= getIntent();
        email = getIntent.getStringExtra("email");
        password = getIntent.getStringExtra("password");
        Log.e(TAG,email);
        Log.e(TAG,password);


        Email.setText(email.toUpperCase());
        Password.setText(password);


        ContentValues parameters = new ContentValues();
        parameters.put("email", email);
        parameters.put("password", password);


//        AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/retrieveUserData.php",parameters){
//            @Override
//            protected void onPostExecute(String output) {
//                //if output = success then go the activity page
//               Log.e(TAG, "Out ="+output.length());
//
//            }
//        };
//        asyncHttpPost.execute();


//        AsyncHTTPPost asyncHttpPost =  new AsyncHTTPPost("https://lamp.ms.wits.ac.za/home/s2143686/retrieveUserData.php",parameters) {
//
//            @Override
//            protected void onPostExecute(String output) {
//               Log.e(TAG,"msg");
//
//
//                //output is UserData
//                //String[] Data = output.split(",");
//
//
//                Log.e(TAG,output);
//
//                Log.e(TAG,"output");
////                //Data[0] is Firstname
////                firstname=Data[0];
////                Log.e(TAG,firstname);
////
////                //Data[1] is Lastname
////                lastname= Data[1];
////                Log.e(TAG,lastname);
////
//////                //Data[2] is UserIDno. // Not Used
////                username = Data[3];
////                Log.e(TAG,username);
//
////                //Data[3] is Username
////                UserName.setText(Data[3].toUpperCase());
//
//            }
//        };
//
//
//         asyncHttpPost.execute();




    }

}
