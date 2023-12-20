package com.example.demo.MoviePractice.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieDto {
    Integer id;
    String name;
    Integer price;
    List<ReviewDto> reviews;
}
