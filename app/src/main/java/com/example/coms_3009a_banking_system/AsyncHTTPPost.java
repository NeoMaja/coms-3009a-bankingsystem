package com.example.coms_3009a_banking_system;

import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class AsyncHTTPPost extends AsyncTask<String, String, String> {
    String address;
    ContentValues parameters;

    public AsyncHTTPPost(String address, ContentValues parameters) {
        this.address = address;
        this.parameters = parameters;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            if (parameters.size() > 0) {
                connection.setDoInput(true);
                Uri.Builder builder = new Uri.Builder();
                for (String s : parameters.keySet()) {
                    builder.appendQueryParameter(s, parameters.getAsString(s));
                }

                String query = builder.build().getEncodedQuery();
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.readLine();
            reader.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
