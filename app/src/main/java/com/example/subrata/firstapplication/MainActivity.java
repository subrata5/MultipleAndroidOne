package com.example.subrata.firstapplication;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subrata.firstapplication.EmojiKeyboardApp.EmojiKeyboard;
import com.example.subrata.firstapplication.FcmDbReadWrite.FcmDbRealtime;
import com.example.subrata.firstapplication.FontAwsomeAndroidLib.FontAwsome;
import com.example.subrata.firstapplication.GetOsRetrofit.GetOsRetro;
import com.example.subrata.firstapplication.InfiniteScrollExample.InfiniteScrollActivity;
import com.example.subrata.firstapplication.MvpPatternValidation.MvpPattern;
import com.example.subrata.firstapplication.UI.LoginUi;
import com.example.subrata.firstapplication.asyncTaskExample.AsyncTaskDemo;
import com.example.subrata.firstapplication.barCodeScanner.BarCodeScanner;
import com.example.subrata.firstapplication.glideExample.GlideActivity;
import com.example.subrata.firstapplication.listViewExample.ListViewAndroid;
import com.example.subrata.firstapplication.recyclerViewExample.RecyclerViewActivity;
import com.example.subrata.firstapplication.retrofitExample.RetroFit;
import com.example.subrata.firstapplication.volleyExample.VolleyActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private String[] items = new String[]{"Async Demo", "Bar code Scanner", "Glide", "Infinite Scroll",
            "Movie List", "RecyclerView", "Retrofit", "Login", "Volley Users", "WebView Search", "GetOsRetro", "MVP pattern",
            "FcmDbRealtime", "FontAwesome Library", "Emoji Keyword"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvSample = findViewById(R.id.tvSample);
        listView = findViewById(R.id.listview);

        AssetManager assetManager = getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager, String.format(Locale.US, "Fonts/%s", "BlackAndWhitePicture-Regular.ttf"));
        tvSample.setTypeface(typeface);

        //Populate the listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        //set on click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "position: "+position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, AsyncTaskDemo.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, BarCodeScanner.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, GlideActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(MainActivity.this, InfiniteScrollActivity.class);
                    startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(MainActivity.this, ListViewAndroid.class);
                    startActivity(intent);
                } else if (position == 5) {
                    Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                    startActivity(intent);
                } else if (position == 6) {
                    Intent intent = new Intent(MainActivity.this, RetroFit.class);
                    startActivity(intent);
                } else if (position == 7) {
                    Intent intent = new Intent(MainActivity.this, LoginUi.class);
                    startActivity(intent);
                } else if (position == 8) {
                    Intent intent = new Intent(MainActivity.this, VolleyActivity.class);
                    startActivity(intent);
                } else if (position == 9) {
                    Intent intent = new Intent(MainActivity.this, WebviewSearch.class);
                    startActivity(intent);
                } else if (position == 10) {
                    Intent intent = new Intent(MainActivity.this, GetOsRetro.class);
                    startActivity(intent);
                } else if (position == 11) {
                    Intent intent = new Intent(MainActivity.this, MvpPattern.class);
                    startActivity(intent);
                } else if (position == 12) {
                    Intent intent = new Intent(MainActivity.this, FcmDbRealtime.class);
                    startActivity(intent);
                }else if(position == 13){
                    Intent intent = new Intent(MainActivity.this, FontAwsome.class);
                    startActivity(intent);
                }else if(position == 14){
                    Intent intent = new Intent(MainActivity.this, EmojiKeyboard.class);
                    startActivity(intent);
                }

            }
        });
    }
}
