package com.example.demo.controller;

import com.example.demo.model.UserLoginDto;
import com.example.demo.model.UserSignUpDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;    // 제어의 역전 사례 (이제 스프링이 객체 생성 및 관리)

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login(UserLoginDto userLoginDto) {
        if (userService.login(userLoginDto)) {
            return "로그인 성공";
        } else {
            return "로그인 실패";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signUp")
    public String signUp(UserSignUpDto userSignUpDto) {
        if (userService.signUp(userSignUpDto)) {
            return "회원가입 성공";
        } else {
            return "회원가입 실패";
        }
    }
}