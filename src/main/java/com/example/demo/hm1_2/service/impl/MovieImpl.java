package com.example.demo.hm1_2.service.impl;
import com.example.demo.hm1_2.pojo.Movie;
import com.example.demo.hm1_2.pojo.dto.YearCount;
import com.example.demo.hm1_2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovieImpl implements MovieService {

    @Value("${api.movieUrl}")
    private String movieUrl;
    private final RestTemplate restTemplate;
    private final ExecutorService executorService;

    @Autowired
    public MovieImpl(RestTemplate restTemplate, ExecutorService executorService) {
        this.restTemplate = restTemplate;
        this.executorService = executorService;
    }

    @Override
    public YearCount getCountByYear(int targetYear) throws InterruptedException, ExecutionException {
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 1; i <= 277; i++) {
            final int page = i;
            tasks.add(() -> {
                String url = movieUrl+"?page="+page;
                System.out.println(url);
                Movie response = restTemplate.getForObject(url, Movie.class, page);
                if (response != null && response.getData() != null) {
                    return (int) Stream.of(response.getData())
                            .filter(data -> data.getYear() == targetYear)
                            .count();
                }
                return 0;
            });
        }

        List<Future<Integer>> results = executorService.invokeAll(tasks);
        int totalCount = 0;

        for (Future<Integer> result : results) {
            totalCount += result.get();
        }

        return new YearCount(totalCount, targetYear);
    }

    @Override
    public Movie getMoviesByPage(int page) {
        String url = movieUrl+"?page="+page;
        return restTemplate.getForObject(url, Movie.class);
    }

}
