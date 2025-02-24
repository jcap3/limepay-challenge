package com.example.limepay.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        @JsonProperty("Title")
        private String title;
        @JsonProperty("Year")
        private int year;
        @JsonProperty("Rated")
        private String rated;
        @JsonProperty("Released")
        private String released;
        @JsonProperty("Runtime")
        private String runtime;
        @JsonProperty("Genre")
        private String genre;
        @JsonProperty("Director")
        private String director;
        @JsonProperty("Writer")
        private String writer;
        @JsonProperty("Actors")
        private String actors;
    }
}
