package com.example.subrata.firstapplication.listViewExample;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.subrata.firstapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieAdapter extends ArrayAdapter<MovieDataModel> implements View.OnClickListener {

    private Context context;
    private List<MovieDataModel> movieList = new ArrayList<>();


//    public MovieAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<MovieDataModel> list) {
//        super(context, list);
//        this.context = context;
//        movieList = list;
//    }

    public MovieAdapter(@NonNull Context context, @LayoutRes ArrayList<MovieDataModel> list) {
        super(context, 0 , list);
        this.context = context;
        this.movieList = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if(listItem == null)
        {
            listItem = LayoutInflater.from(context).inflate(R.layout.listview_layout, parent, false);
        }

        MovieDataModel currentMovie = movieList.get(position);

        //Set image and text-views
        ImageView poster_image = listItem.findViewById(R.id.imageView);
        poster_image.setImageResource(currentMovie.getImageDrawable());


        TextView tv_heading = listItem.findViewById(R.id.tv_heading);
        tv_heading.setText(currentMovie.getMovieName());


        TextView tv_score = listItem.findViewById(R.id.tv_score);
        tv_score.setText(currentMovie.getImdbRating());


        TextView tv_director = listItem.findViewById(R.id.tv_director);
        tv_director.setText(currentMovie.getDirector());

        //Set the font asset
        final AssetManager assetManager = context.getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager, String.format(Locale.US, "Fonts/%s", "Dosis-Medium.ttf"));
        tv_heading.setTypeface(typeface);
        tv_score.setTypeface(typeface);
        tv_director.setTypeface(typeface);

        return listItem;
    }

    @Override
    public void onClick(View v) {

//        int position  = (int) v.getTag();
//        Object object = getItem(position);
//        MovieDataModel movieDataModel = (MovieDataModel)object;
//
//        String movie_name = movieDataModel.getMovieName();
//        Toast.makeText(context, movie_name, Toast.LENGTH_SHORT).show();


    }
}
