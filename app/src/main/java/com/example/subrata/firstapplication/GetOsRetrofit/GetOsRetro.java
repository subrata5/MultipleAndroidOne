package com.example.subrata.firstapplication.GetOsRetrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.subrata.firstapplication.R;

public class GetOsRetro extends AppCompatActivity {

    private Button btnAndroid, btnIos;

    private void init()
    {
        btnAndroid = findViewById(R.id.android);
        btnIos = findViewById(R.id.ios);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_os_retro);

        init();

        //Set on click listeners to the buttons
        btnAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetOsRetro.this, ListActivity.class);
                intent.putExtra("type", "android");
                startActivity(intent);
            }
        });

        btnIos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetOsRetro.this, ListActivity.class);
                intent.putExtra("type", "ios");
                startActivity(intent);
            }
        });
    }
}
