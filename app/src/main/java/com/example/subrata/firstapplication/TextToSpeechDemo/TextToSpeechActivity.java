package com.example.subrata.firstapplication.TextToSpeechDemo;

import android.os.Build;
import android.speech.tts.Voice;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import com.example.subrata.firstapplication.R;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private String TAG = TextToSpeechActivity.class.getSimpleName();
    private EditText et_text;
    private Button btn_speakOut;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        //Initialize the views
        et_text = findViewById(R.id.et_text);
        btn_speakOut = findViewById(R.id.btn_speakOut);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale locale = new Locale( "hi_IN");
                    int ttsLang = textToSpeech.setLanguage(locale);

                    //set pitch
                    textToSpeech.setPitch((float) 1); // default

                    //Speech rate
                    textToSpeech.setSpeechRate((float) 1);

                    //set male or female voice
                    Set<String> a=new HashSet<>();
                    a.add("female");//here you can give male if you want to select mail voice.

                    Voice v=new Voice("bn-in-x-cfn#female_3-local",new Locale("hi_IN"),400,200,true,a);
                    textToSpeech.setVoice(v);



                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Set on click listener for the button click btn_speakOut
        btn_speakOut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                speakOut();

               // Log.e(TAG, "List of Locales: "+ Arrays.toString(Locale.getAvailableLocales()));
                //Log.e(TAG, "Languages: "+textToSpeech.getAvailableLanguages());
                String voices  = String.valueOf(textToSpeech.getVoices());
                Log.e(TAG, "Voices: "+voices);
            }
        });


    }

    /**
     *
     * Get Text from the Edit_text for the user entered text
     * and speak out the same
     *
     */
    private void speakOut() {
        String userEnteredtext = et_text.getText().toString();
        int speechStatus = textToSpeech.speak(userEnteredtext, TextToSpeech.QUEUE_FLUSH, null);

        if(speechStatus == TextToSpeech.ERROR)
        {
            Log.e(TAG, "Error in converting text to speech");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(textToSpeech != null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    public void onInit(int status) {

    }
}
