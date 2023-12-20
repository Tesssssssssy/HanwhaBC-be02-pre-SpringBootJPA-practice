package com.example.demo.MoviePractice.service;

import com.example.demo.MoviePractice.model.Movie;
import com.example.demo.MoviePractice.model.Review;
import com.example.demo.MoviePractice.model.ReviewDto;
import com.example.demo.MoviePractice.repository.ReviewRepository;
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
        // review를 생성할 Movie의 id값을 Integer parameter로 따로 받는다.

        reviewRepository.save(Review.builder()
                .movie(Movie.builder().id(movieId).build())
                .id(reviewDto.getId())
                .star(reviewDto.getStar())
                .text(reviewDto.getText())
                .build());
        // reviewRepository에 저장하는데
        // reviewDto로 받은 값들을 Review Entity로 변환하며 저장.
        // 그리고 입력받은 Integer movieId를 Review Entity의 Movie movie의 id로 저장.
    }

    public List<Review> list() {
        return reviewRepository.findAll();
        // 나는 review 조회할 때 Movie 관련 정보는 조회하지 않을 것이므로
        // 단순 findAll() 메소드 실행.
    }

/*
    <이 버전은 review 조회 시 Movie 관련 정보도 조회할 때 사용하는 메소드>

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
        // Review 테이블에서 입력받은 id로 findById() 메소드 실행해 Review 하나를 조회한 후
        // findById() 메소드는 Optional 타입을 변환하기 때문에
        // Optional의 Review 타입을 저장할 수 있는 result 변수에 조회한 결과값 저장.

        if (result.isPresent()) {
            // 만약 조회한 결과값이 있다면

            Review review = result.get();
            // 일단 조회한 review Entity 하나 가져온다.

            return ReviewDto.builder()
                    .id(review.getId())
                    .star(review.getStar())
                    .text(review.getText())
                    .build();
            // 조회한 결과 데이터는 Review Entity이니까
            // reviewDto로 변환해주고 reviewDto를 반환.

        } else {
            return null;
            // 조회한 결과값이 없다면 null 반환
        }
    }

/*
    <review 하나 조회할 때 그 review의 Movie도 함께 조회할 때 사용하는 메소드>

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
        // review 테이블에서 reviewDto에 id가 있으니까
        // 그 id를 가져와서 findById() 메소드의 parameter로 사용해서 findById(() 메소드 실행 후
        // 조죄한 결과를 Optional<Review> 변수에 저장.

        if (result.isPresent()) {
            // 만약 조회한 review 결과가 있다면

            Review review = result.get();
            review.setStar(reviewDto.getStar());
            review.setText(reviewDto.getText());
            reviewRepository.save(review);
            // Review 타입의 review 변수에 조죄한 결과값 저장.
            // 그리고 그 review의 star, text 값을
            // api 실행 시 입력받은 reviewDto에서 받아서 그 값으로 settter 이용해 수정.
            // 수정한 Review 객체 review를 save.
        }
    }

    public void delete(Integer id) {
        reviewRepository.delete(Review.builder().id(id).build());
        // 입력받은 id를 Review Entity의 id값으로 설정 후
        // 해당 id값을 가진 Review 데이터 삭제.
    }
}
