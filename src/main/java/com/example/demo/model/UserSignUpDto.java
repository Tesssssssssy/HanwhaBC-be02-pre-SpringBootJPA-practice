package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpDto {
    private String name;
    private String email;
    private String pw;
    private String pwChk;
}
