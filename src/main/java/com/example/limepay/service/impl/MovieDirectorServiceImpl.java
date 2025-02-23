package com.example.limepay.service.impl;

import com.example.limepay.model.client.MoviesApiResponse;
import com.example.limepay.model.director.DirectorsResponse;
import com.example.limepay.service.MovieDirectorService;
import com.example.limepay.service.MovieRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieDirectorServiceImpl implements MovieDirectorService {

    @Autowired
    private MovieRestClientService movieRestClientService;

    @Override
    public DirectorsResponse getDirectorsWithMoreThanXMovies(int threshold) {

        Map<String, Integer> directorMovies = new HashMap<>();
        Set<String> directors = new HashSet<>();

        int page = 1;
        int totalPages;
        do {
            MoviesApiResponse moviesApiResponse = movieRestClientService.getMovies(page);
            totalPages = moviesApiResponse.getTotal_pages();
            moviesApiResponse.getData().stream()
                    .map(MoviesApiResponse.Movie::getDirector)
                    .forEach(director -> {
                        int resultingMoviesDirected = directorMovies.compute(director, (directorKey, moviesDirected) -> moviesDirected == null ? 1 : moviesDirected + 1);
                        if (resultingMoviesDirected > threshold) {
                            directors.add(director);
                        }
                    });
        } while(page++ < totalPages);

        return DirectorsResponse
                .builder()
                .directors(directors.stream().sorted().toList())
                .build();
    }
}

