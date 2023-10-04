package com.nhnacademy;

public class Movie {
    private Object movieId;
    private Object title;
    private Object genres;

    public void setMovieId(Object movieId) {
        this.movieId = movieId;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public void setGeneres(Object genres) {
        this.genres = genres;
    }

    public String toString() {
        return this.movieId + " , " + this.title + " , " + this.genres;
    }

}