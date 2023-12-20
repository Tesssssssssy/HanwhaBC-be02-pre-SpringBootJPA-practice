package com.example.demo.MoviePractice.service;

import com.example.demo.MoviePractice.model.Movie;
import com.example.demo.MoviePractice.model.MovieDto;
import com.example.demo.MoviePractice.model.Review;
import com.example.demo.MoviePractice.model.ReviewDto;
import com.example.demo.MoviePractice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    // Service 생성자로 Repository 의존성 주입.

    public void create(MovieDto movieDto) {
        /*
            Dto를 Entity로 만들어 주어야 함.
            그래서 DTO로 받은 애를 Movie Entity로 만들기 위해
            Movie.builder()를 사용하고
            MovieDto에서 받은 값들을 Entity에 할당한다.
        */
        movieRepository.save(Movie.builder()
                .name(movieDto.getName())
                .price(movieDto.getPrice())
                .build());
    }

    public List<MovieDto> list() {
        List<Movie> result = movieRepository.findAll();
        // 우선 Movie 테이블에 있는 데이터들을 모두 가져와서 List로 저장.

        List<MovieDto> movieDtos = new ArrayList<>();
        // Entity를 직접 반환하는 것이 아닌 MovieDto로 반환하기 위해
        // Movie List를 MovieDto로 바꿔서 담을 List<MovieDto> 선언.

        for (Movie movie : result) {
            // Movie 테이블에서 가져온 데이터들을 하나씩 반복

            List<Review> reviews = movie.getReviews();
            // movie 데이터 마다 movie에 포함된 review들을 List에 저장.

            List<ReviewDto> reviewDtos = new ArrayList<>();
            // Movie 조회 시 Movie에 남긴 review들도 함께 조회, 반환하기 위해
            // ReviewDto List도 선언.

            for (Review review : reviews) {
                // movie에 포함된 review들을 하나씩 반복.

                ReviewDto reviewDto = ReviewDto.builder()
                        .id(review.getId())
                        .text(review.getText())
                        .star(review.getStar())
                        .build();
                // movie에서 가져온 review들은 Review Entity이기 때문에
                // 이를 ReviewDto로 변환하는 과정.

                reviewDtos.add(reviewDto);
                // reviewDto로 변환한 데이터들을 reviewDto들을 담을 수 있는 List에 저장.
            }

            MovieDto movieDto = MovieDto.builder()
                    .id(movie.getId())
                    .name(movie.getName())
                    .price(movie.getPrice())
                    .reviews(reviewDtos)
                    .build();
            // review Entity들을 먼저 reviewDto로 변환하는 과정이 끝나고
            // 이제 movie Entity들을 movieDto로 변환하는 과정.

            movieDtos.add(movieDto);
            // 변환한 movieDto들을 movieDto List에 저장하고 최종 반환.
        }
        return movieDtos;
    }


    public MovieDto read(Integer id) {
        Optional<Movie> result = movieRepository.findById(id);
        // findById 메소드는 Optional 타입을 반환.
        // 그래서 Movie 타입 객체를 담을 수 있도록 한다.

        if (result.isPresent()) {
            // 만약 Movie 테이블에서 id값으로 조회한 데이터가 있다면

            Movie movie = result.get();
            // db에서 조회한 Movie Entity 데이터를 Movie 객체 타입의 movie 변수에 일단 저장.

            List<ReviewDto> reviewDtos = new ArrayList<>();
            // 가져온 Movie에 포함된 review Entity들을 reviewDto로 변환해서
            // 반환해야 하니까 이를 저장할 List<ReviewDto> 선언.

            for (Review review : movie.getReviews()) {
                // db에서 조회한 Movie 객체에 포함된 리뷰들을 가져와서 하나씩 반복.

                reviewDtos.add(ReviewDto.builder()
                        .id(review.getId())
                        .star(review.getStar())
                        .text(review.getText())
                        .build());
                // review Entity들을 reviewDto로 변환하고
                // reviewDto를 담을 수 있는 List에 추가.
            }

            return MovieDto.builder()
                    .id(movie.getId())
                    .price(movie.getPrice())
                    .name(movie.getName())
                    .reviews(reviewDtos)
                    .build();
            // review -> reviewDto 변환이 끝났으므로
            // 이제 movie -> movieDto 변환 과정 진행.
        } else {
            return null;
            // db에서 조회한 Movie Entity 데이터가 없으면 null 반환.
        }
    }

    public void update(MovieDto movieDto) {
        Optional<Movie> result = movieRepository.findById(movieDto.getId());
        // MovieDto에는 id가 포함되어 있으니까 id도 입력받고
        // 그 id를 findById()의 parameter로 사용해서 조회하고
        // findById()는 Optional 타입을 반환하므로 Optional 타입의 변수에 조회한 결과값 저장.

        if (result.isPresent()) {
            Movie movie = result.get();
            movie.setName(movieDto.getName());
            movie.setPrice(movieDto.getPrice());
            movieRepository.save(movie);
        }
        // 만약 조회한 결과가 있다면
        // Movie 객체 타입의 movie 변수에 조회한 데이터 일단 저장.
        // 그리고 movie의 name, price를 입력받은 movieDto의 name, price로 setter 이용해 값 변경.
        // movieRepository에 Movie 타입의 movie 저장.
    }

    public void delete(Integer id) {
        movieRepository.delete(Movie.builder().id(id).build());
        // movieRepository에 입력받은 id값을 Movie Entity의 id값으로 설정해서
        // delete 수행.
    }
}
