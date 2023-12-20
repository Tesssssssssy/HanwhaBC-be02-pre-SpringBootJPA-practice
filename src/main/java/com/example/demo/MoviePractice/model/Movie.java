package com.example.demo.MoviePractice.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "practice_movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToMany (mappedBy = "movie")
    private List<Review> reviews = new ArrayList<>();

    String name;
    Integer price;
}
