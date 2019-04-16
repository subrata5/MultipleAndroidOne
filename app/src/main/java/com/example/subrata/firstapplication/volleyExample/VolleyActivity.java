package com.example.subrata.firstapplication.volleyExample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.subrata.firstapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class VolleyActivity extends AppCompatActivity {

    private static final String TAG = VolleyActivity.class.getSimpleName();
    private static final String URL = "https://api.github.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        final RecyclerView userList = findViewById(R.id.userList);
        userList.setLayoutManager(new LinearLayoutManager(VolleyActivity.this));

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Toast.makeText(VolleyActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Success Response: "+response);

                //Parsing the response(Json) using Gson library
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                User[] users =  gson.fromJson(response, User[].class);

                //set adapter to the recycler-view
                userList.setAdapter(new GithubAdapter(VolleyActivity.this, users));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG, "Error Response: "+error);
                Toast.makeText(VolleyActivity.this, "Somethings went wrong", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue = Volley.newRequestQueue(VolleyActivity.this);
        queue.add(request);
    }
}
