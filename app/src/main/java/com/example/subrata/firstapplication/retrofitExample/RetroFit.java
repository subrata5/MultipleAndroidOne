package com.example.subrata.firstapplication.retrofitExample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.subrata.firstapplication.PostList;
import com.example.subrata.firstapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroFit extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro_fit);

        recyclerView = findViewById(R.id.postList);
        recyclerView.setLayoutManager(new LinearLayoutManager(RetroFit.this));

        getData();
    }

    private void getData()
    {
        Call<PostList> postList = BloggerAPI.getService().getPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list =    response.body();
                recyclerView.setAdapter(new PostAdapter(RetroFit.this, list.getItems()));
                Toast.makeText(RetroFit.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(RetroFit.this, "Error occoured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
