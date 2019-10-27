package com.akulinski.crimetivity.pointsaftyservice.web;

import lombok.Data;

import java.io.Serializable;

@Data
public class CheckDTO implements Serializable {

    private String city;
    private String lat;
    private String lon;
}
