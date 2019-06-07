package com.example.subrata.firstapplication.FcmDbReadWrite;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.subrata.firstapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FcmDbRealtime extends AppCompatActivity implements ValueEventListener {


    private TextView headingText;
    private EditText headingInput;
    private Button headingSubmit;
    private RadioButton rbRed, rbBlue;

    //database part variables
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReferenceRoot = firebaseDatabase.getReference();
    private DatabaseReference databaseReferenceHeading = databaseReferenceRoot.child("heading");
    private DatabaseReference databaseReferenceColor = databaseReferenceRoot.child("fontcolor");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcm_db_realtime);

        //Initialize the views
        headingText = findViewById(R.id.headingText);
        headingInput = findViewById(R.id.headingInput);
        headingSubmit = findViewById(R.id.submitHeading);
        rbRed = findViewById(R.id.rbRed);
        rbBlue = findViewById(R.id.rbBlue);


    }

    //Click listener for the button to click to submit the heading
    public void onSubmitClicked(View view)
    {
            String heading = headingInput.getText().toString();
            databaseReferenceHeading.setValue(heading);
    }

    //Radio button on click listener
    @SuppressLint("ResourceType")
    public void onRadioButtonClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rbRed:
                databaseReferenceColor.setValue("red");
                break;

            case R.id.rbBlue:
                databaseReferenceColor.setValue("blue");
                break;

        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        if(dataSnapshot.getValue(String.class) != null)
        {
            String key = dataSnapshot.getKey();
            if(key.equals("heading"))
            {
                String heading = dataSnapshot.getValue(String.class);
                headingText.setText(heading);
            }
            else if(key.equals("fontcolor"))
            {
                    String color = dataSnapshot.getValue(String.class);
                    if(color.equals("red"))
                    {
                        headingText.setTextColor(ContextCompat.getColor(this, R.color.color_red));
                        rbRed.setChecked(true);
                    }
                    else if(color.equals("blue"))
                    {
                        headingText.setTextColor(ContextCompat.getColor(this, R.color.color_blue));
                        rbBlue.setChecked(true);
                    }
            }
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReferenceHeading.addValueEventListener(this);
        databaseReferenceColor.addValueEventListener(this);
    }
}
