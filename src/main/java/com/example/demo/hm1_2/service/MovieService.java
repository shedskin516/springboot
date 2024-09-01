package com.example.demo.hm1_2.service;

import com.example.demo.hm1_2.pojo.Movie;
import com.example.demo.hm1_2.pojo.dto.YearCount;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public interface MovieService {
    YearCount getCountByYear(int year) throws InterruptedException, ExecutionException;
    Movie getMoviesByPage(int page);
}
