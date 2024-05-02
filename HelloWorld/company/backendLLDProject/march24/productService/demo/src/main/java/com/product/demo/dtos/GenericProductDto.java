package com.product.demo.dtos;

import lombok.Getter;
import lombok.Setter;
    @Getter
    @Setter
    public class GenericProductDto {
        private String id;
        private String title;
        private float price;
        private String category;
        private String description;
        private String image;
}
