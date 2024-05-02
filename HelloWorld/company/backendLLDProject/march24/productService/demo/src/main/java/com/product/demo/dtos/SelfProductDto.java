package com.product.demo.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Getter
@Setter
public class SelfProductDto {
    private long id;
    private String title;
    private float price;
    private String category;
    private String description;
    private String image;
}
