package com.product.demo.service;

import com.product.demo.Exception.LimitTooLargeException;
import com.product.demo.Exception.NoProductInListException;
import com.product.demo.Exception.NotFoundException;
import com.product.demo.dtos.GenericProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public GenericProductDto getProductById(UUID id) throws NotFoundException;

    public GenericProductDto createProduct(GenericProductDto genericProductDto);

    public List<GenericProductDto> getAllProducts();

    public GenericProductDto deleteProduct(UUID id) throws NotFoundException;

    public GenericProductDto updateProduct(UUID id , GenericProductDto genericProductDto) throws NotFoundException;

    public List<GenericProductDto> getProductsLimit(Long limit) throws NoProductInListException, LimitTooLargeException;
}
