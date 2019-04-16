package com.example.subrata.firstapplication.listViewExample;

public class MovieDataModel {

    private int imageDrawable;
    private String movieName, imdbRating, director;

    //Constructor to use a instance of the java class
    public  MovieDataModel(int imageDrawable, String  movieName, String imdbRating, String director)
    {
        this.imageDrawable = imageDrawable;
        this.movieName = movieName;
        this.imdbRating = imdbRating;
        this.director = director;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
