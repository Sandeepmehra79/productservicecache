package com.product.demo.mapper;

import com.product.demo.dtos.FakeStoreProductDto;
import com.product.demo.dtos.GenericProductDto;
import com.product.demo.model.Product;

public class ProductToGenericProductDto {
    public  static GenericProductDto convert(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(new String(product.getId().toString()));
        genericProductDto.setTitle(new String(product.getTitle()));
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setCategory(new String(product.getCategory()));
        genericProductDto.setDescription(new String(product.getDescription()));
        genericProductDto.setImage(new String(product.getImage()));
        return genericProductDto;
    }
}
