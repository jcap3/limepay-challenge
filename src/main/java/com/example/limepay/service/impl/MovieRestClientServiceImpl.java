package com.example.limepay.service.impl;

import com.example.limepay.configuration.properties.MoviesApiProperties;
import com.example.limepay.model.client.MoviesApiResponse;
import com.example.limepay.service.MovieRestClientService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieRestClientServiceImpl implements MovieRestClientService {

    private final WebClient webClient;

    private final MoviesApiProperties moviesApiProperties;

    public MovieRestClientServiceImpl(WebClient webClient, MoviesApiProperties moviesApiProperties) {
        this.webClient = webClient;
        this.moviesApiProperties = moviesApiProperties;
    }

    @Cacheable (value = "moviesCache", key = "#page")
    @Override
    public MoviesApiResponse getMovies(int page) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(moviesApiProperties.getMovies())
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(MoviesApiResponse.class)
                .block();
    }
}
