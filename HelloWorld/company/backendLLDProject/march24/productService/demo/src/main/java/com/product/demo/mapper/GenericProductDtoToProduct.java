package com.product.demo.mapper;

import com.product.demo.dtos.GenericProductDto;
import com.product.demo.model.Product;

import java.util.UUID;

public class GenericProductDtoToProduct {
    public static Product convert(GenericProductDto genericProductDto){
        Product product = new Product();
//        product.setId(UUID.fromString(genericProductDto.getId()));
        product.setTitle(genericProductDto.getTitle());
        product.setPrice(genericProductDto.getPrice());
        product.setCategory(genericProductDto.getCategory());
        product.setDescription(genericProductDto.getDescription());
        product.setImage(genericProductDto.getImage());
        return product;
    }
}
