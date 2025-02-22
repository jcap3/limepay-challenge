package com.example.limepay.controller;

import com.example.limepay.model.Response;
import com.example.limepay.util.ResponseConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MovieDirectorController {


    @GetMapping
    public ResponseEntity<Response<String>> getMovieDirector() {
        return ResponseConverter.convert("test");
    }
}
