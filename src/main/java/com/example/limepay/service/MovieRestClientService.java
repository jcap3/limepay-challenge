package com.example.limepay.service;

import com.example.limepay.model.client.MoviesApiResponse;

public interface MovieRestClientService {

    MoviesApiResponse getMovies(int page);
}
