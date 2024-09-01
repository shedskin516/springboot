package com.example.demo.hm1_2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieItem {
    private String Title;
    @JsonProperty("Year")
    private int year;
    private String imdbID;
}
