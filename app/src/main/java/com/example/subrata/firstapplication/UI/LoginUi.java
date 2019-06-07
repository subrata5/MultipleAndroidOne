package com.example.subrata.firstapplication.UI;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.subrata.firstapplication.R;

import java.util.Locale;

public class LoginUi extends AppCompatActivity {

    private TextView welcometext, loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ui);


//        Typeface face = Typeface.createFromAsset(getAssets(),
//                "fonts/epimodem.ttf");
//        tv.setTypeface(face);

        welcometext = findViewById(R.id.welcome);
        loginText = findViewById(R.id.login);

        final AssetManager assetManager = getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager, String.format(Locale.US, "Fonts/%s", "Mirza/Mirza-SemiBold.ttf"));
        welcometext.setTypeface(typeface);

        Typeface typeface2 = Typeface.createFromAsset(assetManager, String.format(Locale.US, "Fonts/%s", "Mirza/Mirza-Bold.ttf"));
        loginText.setTypeface(typeface2);

    }
}
