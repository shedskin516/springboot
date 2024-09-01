package com.example.demo.hm1_2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieItem {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private int year;
    private String imdbID;
}
