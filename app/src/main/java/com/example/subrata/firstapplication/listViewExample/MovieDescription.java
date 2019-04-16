package com.example.subrata.firstapplication.listViewExample;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.subrata.firstapplication.R;

public class MovieDescription extends AppCompatActivity {

    private ImageView img_back;

    //Initialization of the views
    private void init()
    {
        img_back = findViewById(R.id.img_back);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        //Set Animation
        setUpWindowAnimation();

        //Method call for initialization of the view
        init();

        //on click functions
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setUpWindowAnimation() {
//        Fade fade = (Fade) TransitionInflater.from(MovieDescription.this).inflateTransition(R.transition.activity_fade);
//        getWindow().setExitTransition(fade);

        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }

}
