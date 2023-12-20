package com.example.demo.MoviePractice.controller;

import com.example.demo.MoviePractice.model.ReviewDto;
import com.example.demo.MoviePractice.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(Integer movieId, ReviewDto reviewDto) {
        reviewService.create(movieId, reviewDto);
        return ResponseEntity.ok().body("review 생성 완료");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {
        return ResponseEntity.ok().body(reviewService.list());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read(Integer id) {
        return ResponseEntity.ok().body(reviewService.read(id));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(ReviewDto reviewDto) {
        reviewService.update(reviewDto);
        return ResponseEntity.ok().body("review 수정 완료");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity delete(Integer id) {
        reviewService.delete(id);
        return ResponseEntity.ok().body("review 삭제 성공");
    }
}
