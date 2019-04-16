package com.example.subrata.firstapplication.recyclerViewExample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.subrata.firstapplication.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView programmingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        //Initialize the view
        programmingList = findViewById(R.id.programmingList);

        programmingList.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
        String[] languages = {"Java", "Javascript", "C", "C++", "C#", "Php", "Python", "Ruby", "Swift",
        "Kotlin", "Kojo", "Golo", "VisualBasic", "J#", "Jython", "Kodu", "Kv", "LISA", "Lasso"};
        programmingList.setAdapter(new ProgrammingAdapter(languages));
    }
}
