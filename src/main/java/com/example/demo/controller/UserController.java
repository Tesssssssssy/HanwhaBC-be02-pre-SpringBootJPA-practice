package com.example.demo.controller;

import com.example.demo.model.UserLoginDto;
import com.example.demo.model.UserSignUpDto;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;    // 제어의 역전 사례 (이제 스프링이 객체 생성 및 관리)

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<Object> login(UserLoginDto userLoginDto) {
        if (userService.login(userLoginDto)) {
            return ResponseEntity.ok().body("로그인 성공");
        } else {
            return ResponseEntity.badRequest().body("로그인 실패");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signUp")
    public ResponseEntity<Object> signUp(UserSignUpDto userSignUpDto) {
        if (userService.signUp(userSignUpDto)) {
            return ResponseEntity.ok().body(userSignUpDto);
        } else {
            return ResponseEntity.badRequest().body("회원가입 실패");
        }
    }
}