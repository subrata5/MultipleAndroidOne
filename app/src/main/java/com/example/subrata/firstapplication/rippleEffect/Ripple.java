package com.example.subrata.firstapplication.rippleEffect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.subrata.firstapplication.R;
import com.skyfishjy.library.RippleBackground;

public class Ripple extends AppCompatActivity {

    private boolean clicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);

        //Initialize the views
        final RippleBackground rippleBackground = findViewById(R.id.content);
        ImageView imageView = findViewById(R.id.imageView);

        //image view on click listener
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clicked) {
                    rippleBackground.startRippleAnimation();
                    clicked = false;
                } else {
                    clicked = true;
                    rippleBackground.stopRippleAnimation();
                }

            }
        });
    }
}


