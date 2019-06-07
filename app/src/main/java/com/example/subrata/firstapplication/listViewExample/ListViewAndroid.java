package com.example.subrata.firstapplication.listViewExample;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.subrata.firstapplication.R;
import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.ValueBar;

import java.util.ArrayList;

public class ListViewAndroid extends AppCompatActivity {

    private ListView listView;
    private ImageView img_menu;
    private MovieAdapter movieAdapter;
    private LinearLayout topHeader;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_android);

        //set up animation
        setUpWindowAnimation();

        //Initialization of the view
       listView = findViewById(R.id.listView);
       img_menu = findViewById(R.id.img_menu);
       topHeader = findViewById(R.id.topHeader);

        ArrayList<MovieDataModel> movieList = new ArrayList<>();
        movieList.add(new MovieDataModel(R.drawable.one_ralph,"Ralph Breaks the Internet", "7.6", "Rich Moore, Phil Johnston" ));
        movieList.add(new MovieDataModel(R.drawable.two_avangers,"Avengers: Infinity War", "8.5", "Anthony Russo, Joe Russo"));
        movieList.add(new MovieDataModel(R.drawable.three_nightscholl,"Night School", "5.6", "Malcolm D. Lee"));
        movieList.add(new MovieDataModel(R.drawable.four_oldman,"The Old Man & the Gun", "7.3", "David Lowery"));
        movieList.add(new MovieDataModel(R.drawable.five_aqua,"Aquaman", "8.8", "James Wan"));
        movieList.add(new MovieDataModel(R.drawable.six_green,"Green Book", "8.3", "Peter Farrelly"));
        movieList.add(new MovieDataModel(R.drawable.seven_bumblee,"Bumblebee", "8.4", "Travis Knight"));
        movieList.add(new MovieDataModel(R.drawable.eight_dark,"The Dark Knight", "9", "Christopher Nolan"));
        movieList.add(new MovieDataModel(R.drawable.nine_tully,"Tully", "9", "Jason Reitman"));
        movieList.add(new MovieDataModel(R.drawable.ten_toy,"Toy Story 3", "8.3", "Lee Unkrich"));


        //Set Movie Adapter
        movieAdapter = new MovieAdapter(getApplicationContext(), movieList);
        listView.setAdapter(movieAdapter);


        //Set onclick listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object object = listView.getItemAtPosition(position);
                MovieDataModel movieDataModel = (MovieDataModel)object;

                String movie_name = movieDataModel.getMovieName();
                Toast.makeText(ListViewAndroid.this, movie_name, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ListViewAndroid.this, MovieDescription.class));
            }
        });
        
        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAlert();
            }
        });
    }

    private void OpenAlert() {
        final String[] item = {"Change Theme", "About"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ListViewAndroid.this);
        builder.setTitle("Menu")
                .setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ListViewAndroid.this, item[which]+ " is Clicked", Toast.LENGTH_SHORT).show();
                        if(item[which].contentEquals("Change Theme"))
                        {
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ListViewAndroid.this);
                            dialogBuilder.setTitle("Set Theme Colour");
                            dialogBuilder.setIcon(getResources().getDrawable(R.drawable.dialog_menu_purple));
                            LayoutInflater inflater = ListViewAndroid.this.getLayoutInflater();
                            View dialogView = inflater.inflate(R.layout.colour_picker_layout, null);
                            dialogBuilder.setView(dialogView);

                            //Define the views inside the layout here
                            //edittxt =   dialogView.findViewById(R.id.abc);
                            final ColorPicker picker =  dialogView.findViewById(R.id.picker);
                            final SVBar svBar =  dialogView.findViewById(R.id.svbar);
                            final OpacityBar opacityBar = dialogView.findViewById(R.id.opacitybar);
                            final ValueBar valueBar = dialogView.findViewById(R.id.valuebar);



                            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int color = picker.getColor();
                                    Toast.makeText(ListViewAndroid.this, "Color picked is"+color, Toast.LENGTH_SHORT).show();
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        changeColour(color);
                                    }
                                }
                            });
                            dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(ListViewAndroid.this, "Cancel Pressed", Toast.LENGTH_SHORT).show();
                                }
                            });

                            //Set the alertDialog
                            AlertDialog alertDialog = dialogBuilder.create();
                            alertDialog.show();

                        }

                    }
                });
        builder.setNegativeButton("Dismiss", null);
        builder.setIcon(getResources().getDrawable(R.drawable.dialog_menu_purple));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setUpWindowAnimation() {
//        Slide slide = (Slide) TransitionInflater.from(ListViewAndroid.this).inflateTransition(R.transition.activity_slide);
//        getWindow().setExitTransition(slide);

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeColour(int color)
    {
        Window window = ListViewAndroid.this.getWindow();
        int statusBarColour = color;

        //convert int colour to hex colour
       // String strColor = String.format("#%06X", 0xFFFFFF & color);

        //Make a deeper colour state
       // statusBarColour = (color & 0xfefefe) >> 1;
        statusBarColour = ((color & 0x7E7E7E) >> 1) | (color & 0x808080);
        String strColor = String.format("#%06X", 0xFFFFFF & statusBarColour);


        window.setStatusBarColor(Color.parseColor(strColor));

        topHeader.setBackgroundColor(color);
    }


}
