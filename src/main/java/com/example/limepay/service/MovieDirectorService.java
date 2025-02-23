package com.example.limepay.service;

import com.example.limepay.model.director.DirectorsResponse;

public interface MovieDirectorService {

    DirectorsResponse getDirectorsWithMoreThanXMovies(int threshold);
}
