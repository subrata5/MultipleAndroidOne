package com.example.subrata.firstapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.lang.reflect.Method;

public class WebviewSearch extends AppCompatActivity implements View.OnClickListener {

    WebView mWebView;
    private RelativeLayout container;
    private Button nextButton, closeButton;
    private EditText findBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_search);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        //mWebView.loadUrl("https://granta.com/cracking-up/");
        mWebView.loadUrl("file:///android_asset/index.html");

        nextButton = (Button) findViewById(R.id.nextButton);
        closeButton = (Button) findViewById(R.id.closeButton);
        findBox = (EditText) findViewById(R.id.findBox);
        findBox.setSingleLine(true);
        findBox.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && ((keyCode == KeyEvent.KEYCODE_ENTER))) {
                    mWebView.findAll(findBox.getText().toString());

                    try {
                        // Can't use getMethod() as it's a private method
                        for (Method m : WebView.class.getDeclaredMethods()) {
                            if (m.getName().equals("setFindIsUp")) {
                                m.setAccessible(true);
                                m.invoke(mWebView, true);
                                break;
                            }
                        }
                    } catch (Exception ignored) {
                    } finally {
                        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        // check if no view has focus:
                        View vv = getCurrentFocus();
                        if (vv != null) {
                            inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    }
                }
                return false;
            }
        });
        nextButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == nextButton) {
            mWebView.findNext(true);
        } else if (v == closeButton) {
            container.setVisibility(RelativeLayout.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchview_in_menu, menu);


        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                search();
                return true;
        }
        return true;
    }

    public void search() {
        container = (RelativeLayout) findViewById(R.id.layoutId);
        if (container.getVisibility() == RelativeLayout.GONE) {
            container.setVisibility(RelativeLayout.VISIBLE);
        } else if (container.getVisibility() == RelativeLayout.VISIBLE) {
            container.setVisibility(RelativeLayout.GONE);
        }
    }

    //Back button click function
    //To go the previous web-page, instead of closing the app.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
