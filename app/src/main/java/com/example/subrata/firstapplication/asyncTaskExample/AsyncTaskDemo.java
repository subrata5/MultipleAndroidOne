package com.example.subrata.firstapplication.asyncTaskExample;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subrata.firstapplication.MainActivity;
import com.example.subrata.firstapplication.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.net.Inet4Address;
import java.util.Locale;
import java.util.PriorityQueue;

public class AsyncTaskDemo extends AppCompatActivity {

    TextView tv_description, tv_time ;
    EditText ed_input;
    Button btn_runAsync;
    AVLoadingIndicatorView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_demo);

        //Initialization of the views
        tv_description = findViewById(R.id.tv_description);
        ed_input = findViewById(R.id.ed_input);
        btn_runAsync = findViewById(R.id.btn_run);
        tv_time = findViewById(R.id.tv_time);
        indicator = findViewById(R.id.indicator);

        //Set visibility of the views
        tv_time.setVisibility(View.INVISIBLE);
        indicator.setVisibility(View.INVISIBLE);

        //Set the font asset
        final AssetManager assetManager = getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager, String.format(Locale.US, "Fonts/%s", "Dosis-Medium.ttf"));
        tv_description.setTypeface(typeface);
        ed_input.setTypeface(typeface);
        btn_runAsync.setTypeface(typeface);


        //Button click to start the async task
        btn_runAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
                String sleeptime = ed_input.getText().toString().trim();
                if(sleeptime == null || sleeptime.isEmpty())
                {
                    Toast.makeText(AsyncTaskDemo.this, "Enter Seconds to Sleep", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    btn_runAsync.setPressed(true);
                    asyncTaskRunner.execute(sleeptime);
                }

            }
        });


    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        public AsyncTaskRunner() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // progressDialog = ProgressDialog.show(AsyncTaskDemo.this, "Progress", "Wait for "+ed_input.getText().toString()+ " seconds");
            indicator.setVisibility(View.VISIBLE);
            tv_time.setVisibility(View.VISIBLE);

            //set time in text view
            tv_time.setText("Wait for "+ed_input.getText().toString()+ " seconds");
            final AssetManager assetManager = getAssets();
            Typeface typeface = Typeface.createFromAsset(assetManager, String.format(Locale.US, "Fonts/%s", "Dosis-Medium.ttf"));
            tv_time.setTypeface(typeface);

            //start indicator animation
            indicator.show();


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //progressDialog.dismiss();
            //Toast.makeText(AsyncTaskDemo.this, ""+resp, Toast.LENGTH_SHORT).show();


            indicator.hide();
            tv_time.setText(resp);

            //Set pressed state change.
            btn_runAsync.setPressed(false);

            indicator.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

            //calls on progress update
            publishProgress("Sleeping...");

            try {

                int time = Integer.parseInt(strings[0])*1000;
                Thread.sleep(time);

                resp = "Slept for "+strings[0] + " seconds";

            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            }

            return resp;
        }
    }
}


