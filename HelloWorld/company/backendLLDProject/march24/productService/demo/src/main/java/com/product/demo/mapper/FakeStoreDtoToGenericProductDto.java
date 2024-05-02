package com.product.demo.mapper;

import com.product.demo.dtos.FakeStoreProductDto;
import com.product.demo.dtos.GenericProductDto;
import com.product.demo.model.Product;

import java.util.UUID;

public class FakeStoreDtoToGenericProductDto {
    public static GenericProductDto convert(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        return genericProductDto;
    }
}
