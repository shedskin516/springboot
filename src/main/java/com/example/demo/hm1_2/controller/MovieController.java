package com.example.demo.hm1_2.controller;

import com.example.demo.hm1_2.pojo.Movie;
import com.example.demo.hm1_2.pojo.dto.YearCount;
import com.example.demo.hm1_2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<YearCount> getCountByYear(@PathVariable("year") int year) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(movieService.getCountByYear(year), HttpStatus.OK);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Movie> getMoviesByPage(@PathVariable("page") int page) {
        return new ResponseEntity<>(movieService.getMoviesByPage(page), HttpStatus.OK);
    }
}
