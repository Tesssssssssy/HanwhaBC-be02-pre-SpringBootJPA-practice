package com.example.demo.movie.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class MovieDto {
    Integer id;
    String name;
    Integer price;
    List<ReviewDto> reviews;
}
