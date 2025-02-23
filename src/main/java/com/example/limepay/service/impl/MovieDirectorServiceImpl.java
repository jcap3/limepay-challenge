package com.example.limepay.service.impl;

import com.example.limepay.error.exception.NoDirectorsFound;
import com.example.limepay.model.client.MoviesApiResponse;
import com.example.limepay.model.director.DirectorsResponse;
import com.example.limepay.service.MovieDirectorService;
import com.example.limepay.service.MovieRestClientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class MovieDirectorServiceImpl implements MovieDirectorService {

    private final MovieRestClientService movieRestClientService;

    public MovieDirectorServiceImpl(MovieRestClientService movieRestClientService) {
        this.movieRestClientService = movieRestClientService;
    }

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

        if (directors.isEmpty()) {
            throw new NoDirectorsFound(String.format("No directors found with more than %d directed movies", threshold));
        }

        return DirectorsResponse
                .builder()
                .directors(directors.stream().sorted().toList())
                .build();
    }
}

