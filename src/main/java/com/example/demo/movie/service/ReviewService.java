package com.example.demo.movie.service;

import com.example.demo.movie.model.Movie;
import com.example.demo.movie.model.Review;
import com.example.demo.movie.model.ReviewDto;
import com.example.demo.movie.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Entity인데 id만 잘 세팅해주면 됨.
    // 굳이 조회해서 넣을 필요 없음.
    public void create(Integer movieId, ReviewDto reviewDto) {
        reviewRepository.save(Review.builder()
                .movie(Movie.builder().id(movieId).build())
                .id(reviewDto.getId())
                .star(reviewDto.getStar())
                .text(reviewDto.getText())
                .build());
    }

    public List<Review> list() {
        return reviewRepository.findAll();
    }

/*
     public List<ReviewDto> list() {
        List<Review> result = reviewRepository.findAll();

        List<ReviewDto> reviewDtos = new ArrayList<>();

        for (Review review:result) {
            Movie movie = review.getMovie();
            MovieDto movieDto = MovieDto.builder()
                    .id(movie.getId())
                    .name(movie.getName())
                    .price(movie.getPrice())
                    .build();

            ReviewDto reviewDto = ReviewDto.builder()
                    .id(review.getId())
                    .star(review.getStar())
                    .text(review.getText())
                    .movieDto(movieDto)
                    .build();
            reviewDtos.add(reviewDto);
       }
       return reviewDtos;
     }
*/

    public ReviewDto read(Integer id) {
        Optional<Review> result = reviewRepository.findById(id);
        if (result.isPresent()) {
            Review review = result.get();
            // 일단 review Entity 하나 가져온다.

            return ReviewDto.builder()
                    .id(review.getId())
                    .star(review.getStar())
                    .text(review.getText())
                    .build();
        } else {
            return null;
        }
    }

/*
    public ReviewDto read(Integer id) {
        Optional<Review> result = reviewRepository.findById(id);
        if (result.isPresent()) {
            Review review = result.get();

            return ReviewDto.builder()
                    .id(review.getId())
                    .star(review.getStar())
                    .text(review.getText())
                    .movieDto(MovieDto.builder()
                            .id(review.getMovie().getId())
                            .name(review.getMovie().getName())
                            .price(review.getMovie().getPrice())
                            .build())
                    .build();
        } else {
            return null;
        }
    }
*/

    public void update(ReviewDto reviewDto) {
        Optional<Review> result = reviewRepository.findById(reviewDto.getId());
        if (result.isPresent()) {
            Review review = result.get();
            review.setStar(reviewDto.getStar());
            review.setText(reviewDto.getText());
            reviewRepository.save(review);
        }
    }

    public void delete(Integer id) {
        reviewRepository.delete(Review.builder().id(id).build());
    }
}
