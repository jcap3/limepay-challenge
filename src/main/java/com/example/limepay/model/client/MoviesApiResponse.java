package com.example.limepay.model.client;

import lombok.Getter;

import java.util.List;

@Getter
public class MoviesApiResponse {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<Movie> data;

    @Getter
    public static class Movie {
        private String title;
        private int year;
        private String rated;
        private String released;
        private String runtime;
        private String genre;
        private String director;
        private String writer;
        private String actors;
    }
}
