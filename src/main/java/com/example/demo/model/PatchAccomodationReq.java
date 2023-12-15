package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchAccomodationReq {
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
}
