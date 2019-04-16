package com.example.subrata.firstapplication.volleyExample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.subrata.firstapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

public class UserDetails extends AppCompatActivity {

    private static final String TAG = UserDetails.class.getSimpleName();
    private ImageView ud_imgUser;
    private TextView txtName, txtCompany, txtLocation, txtFollowers, txtFollowing;
    private ImageView imgWebsite, imgGitHub;
    private String website_url, github_url;


    private void init() {
        ud_imgUser = findViewById(R.id.ud_imgUser);
        txtName = findViewById(R.id.txtName);
        txtCompany = findViewById(R.id.txtCompany);
        txtLocation = findViewById(R.id.txtLocation);
        txtFollowers = findViewById(R.id.txtFollowers);
        txtFollowing = findViewById(R.id.txtFollowing);
        imgWebsite = findViewById(R.id.imgWebsite);
        imgGitHub = findViewById(R.id.imgGitHub);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        //initiate the views
        init();

        //get url from intent
        final Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        //Toast.makeText(this, "URL:"+url, Toast.LENGTH_SHORT).show();


        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                UserDetailsPojo userDetailsPojo = gson.fromJson(response, UserDetailsPojo.class);

                //Toast.makeText(UserDetails.this, userDetailsPojo.getName(), Toast.LENGTH_SHORT).show();
                String avatar_url = userDetailsPojo.getAvatarUrl();
                String name = userDetailsPojo.getName();
                String company = userDetailsPojo.getCompany();
                String location = userDetailsPojo.getLocation();
                String followers = String.valueOf(userDetailsPojo.getFollowers());
                String following = String.valueOf(userDetailsPojo.getFollowing());
                website_url = userDetailsPojo.getBlog();
                github_url = userDetailsPojo.getHtmlUrl();


                if (avatar_url != null) {
                    Glide.with(UserDetails.this).load(avatar_url).into(ud_imgUser);
                    txtName.setText(name);
                    txtCompany.setText("Company: " + company);
                    txtLocation.setText("Location: " + location);
                    txtFollowers.setText("Followers: " + followers);
                    txtFollowing.setText("Following: " + following);

                } else {
                    Toast.makeText(UserDetails.this, "No Picture", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error:" + error);
                Toast.makeText(UserDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(UserDetails.this);
        queue.add(request);


        //Set on click listeners for the image views
        imgWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (website_url != null)
                {
                    Intent intent1 = new Intent(UserDetails.this, WebViewActivity.class);
                    intent1.putExtra("url", website_url);
                    startActivity(intent1);
                }
                else
                {
                    Toast.makeText(UserDetails.this, "Sorry! No Link Available", Toast.LENGTH_SHORT).show();
                }
            }
        });


        imgGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (github_url != null)
                {
                    Intent intent1 = new Intent(UserDetails.this, WebViewActivity.class);
                    intent1.putExtra("url", github_url);
                    startActivity(intent1);
                }
                else
                {
                    Toast.makeText(UserDetails.this, "Sorry! No Link Available", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
