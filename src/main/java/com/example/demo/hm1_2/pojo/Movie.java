package com.example.demo.hm1_2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String page;
    private String per_page;
    private String total;
    private String total_pages;
    private MovieItem[] data;
}
