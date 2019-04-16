package com.example.subrata.firstapplication.InfiniteScrollExample;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.example.subrata.firstapplication.R;

import java.util.ArrayList;
import java.util.Arrays;

public class InfiniteScrollActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Adapter adapter;
    private Boolean isScrolling = false;
    private int currentItems, totalItems, scrolledOutItems;
    ArrayList list;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_scroll);


        //Initialize the views
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(InfiniteScrollActivity.this);
        progressBar = findViewById(R.id.progress);

        String[] array = {"Rabindranath Tagore", "Arijit Singh", "AR Rahman", "Sonu Nigam", "Amir Khan", "Paulo Cohelo", "Srikanta Achariya",
                            "Mukesh Ambani", "Sundar Pechai", "Satyam Nedella", "Bill Gates", "Mark Zukerberg", "James Gosling", "Tim berness Less",
                            "Justin Timberlake"};
        list = new ArrayList(Arrays.asList(array));


        adapter = new Adapter(list, InfiniteScrollActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrolledOutItems = layoutManager.findFirstVisibleItemPosition();

                //this condition says that the total maximum number of views to be displayed in the
                //Scroll view has been reached and the user is still scrolling
                //so now the bottom loading bar has to be shown and more data has to be fetched
                //from the database.
                if(isScrolling && (currentItems + scrolledOutItems == totalItems))
                {
                    //data fetch from the server
                    isScrolling = false;
                    fetchData();

                }
            }
        });
    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                        for(int i=0; i<5; i++)
                        {
                            list.add(Math.floor(Math.random()*100) + "");
                            adapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        }
            }
        },5000);
    }
}
