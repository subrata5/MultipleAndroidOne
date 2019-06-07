package com.example.subrata.firstapplication.glideExample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.subrata.firstapplication.R;

public class GlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        ImageView imageView1, imageView2;
        imageView1 = findViewById(R.id.img1);
        imageView2 = findViewById(R.id.img2);

        String[] urls = {
                "https://images.pexels.com/photos/47261/pexels-photo-47261.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
                "https://images.pexels.com/photos/441795/pexels-photo-441795.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
        };

        Glide.with(GlideActivity.this).load(urls[0]).into(imageView1);
        Glide.with(GlideActivity.this).load(urls[1]).into(imageView2);

    }
}
