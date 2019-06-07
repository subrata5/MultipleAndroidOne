package com.example.subrata.firstapplication.EmojiKeyboardApp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.subrata.firstapplication.R;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;

public class EmojiKeyboard extends AppCompatActivity {

    private static final String TAG = EmojiKeyboard.class.getSimpleName();
    private CheckBox checkBox;
    private EmojiconTextView emojiconTextView;
    private EmojiconEditText emojiconEditText;
    private ImageView emojiImageView, sendImageView;
    private View rootView;
    private EmojIconActions emojIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_keyboard);

        //Initia†e the views
        checkBox = findViewById(R.id.checkbox);
        emojiconTextView = findViewById(R.id.textview);
        emojiconEditText = findViewById(R.id.emojiEditText);
        emojiImageView = findViewById(R.id.emoji_button);
        sendImageView = findViewById(R.id.send_button);
        rootView = findViewById(R.id.root_view);

        emojIcon = new EmojIconActions(this, rootView, emojiconEditText, emojiImageView);
        emojIcon.ShowEmojIcon();

        //change the icons for the keyboard and the icons for the smiley
        emojIcon.setIconsIds(R.drawable.ic_action_keyboard, R.drawable.emoji_icon);
        emojIcon.setKeyboardListener(new EmojIconActions.KeyboardListener() {
            @Override
            public void onKeyboardOpen() {
                Log.e(TAG, "Keyboard Opened");
               // Toast.makeText(EmojiKeyboard.this, "Keyboard Opened!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onKeyboardClose() {
                Log.e(TAG, "Keyboard Closed!");
                //Toast.makeText(EmojiKeyboard.this, "Keyboard Closed!", Toast.LENGTH_SHORT).show();
            }
        });

        //Set click listener for the check box check change
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                emojIcon.setUseSystemEmoji(isChecked);
                emojiconTextView.setUseSystemDefault(isChecked);
            }
        });

        //submit button click action
        sendImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = emojiconEditText.getText().toString();
                emojiconTextView.setText(newText);
                emojiconEditText.setText("");
            }
        });
    }
}
