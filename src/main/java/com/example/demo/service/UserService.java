package com.example.demo.service;

import com.example.demo.model.UserLoginDto;
import com.example.demo.model.UserSignUpDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public boolean login(UserLoginDto userLoginDto) {
        if (userLoginDto.getId().equals("test01") && userLoginDto.getPw().equals("qwer1234")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean signUp(UserSignUpDto userSignUpDto) {
        if (!userSignUpDto.getEmail().isBlank() && !userSignUpDto.getName().isBlank() &&
                userSignUpDto.getPw().equals(userSignUpDto.getPwChk())) {
            return true;
        } else {
            return false;
        }
    }
}
