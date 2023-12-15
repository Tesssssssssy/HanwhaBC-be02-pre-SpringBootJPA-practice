package com.example.demo.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAccomodationRes {
    private Integer id;
    private Integer user_id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private String content;
    private Integer max_user;
    private Integer price;
    private String img;
    private Integer hasAirConditioner;
    private Integer hasWashingMachine;
    private Date register_time;
}
