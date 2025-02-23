package com.example.limepay.controller;

import com.example.limepay.model.Response;
import com.example.limepay.model.director.DirectorsResponse;
import com.example.limepay.service.MovieDirectorService;
import com.example.limepay.util.ResponseConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/directors")
public class MovieDirectorController implements MovieDirectorApi {

    private final MovieDirectorService movieDirectorService;

    public MovieDirectorController(MovieDirectorService movieDirectorService) {
        this.movieDirectorService = movieDirectorService;
    }

    @GetMapping("/by-movie-count")
    public ResponseEntity<Response<DirectorsResponse>> getMovieDirector(@RequestParam int threshold) {
        return ResponseConverter.convert(movieDirectorService.getDirectorsWithMoreThanXMovies(threshold));
    }
}
