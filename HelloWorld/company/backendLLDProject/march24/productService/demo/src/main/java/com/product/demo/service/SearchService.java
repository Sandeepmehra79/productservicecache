package com.product.demo.service;

import com.product.demo.dtos.GenericProductDto;
import com.product.demo.mapper.ProductToGenericProductDto;
import com.product.demo.model.Product;
import com.product.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<GenericProductDto> search(String query, int pageSize, int pageNumber) {
        Sort sort = Sort.by( "title").descending()
                .and(Sort.by("price").descending());
        Pageable     pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Product> productPage =  productRepository.findAllByTitleContaining(query, pageable);
        List<GenericProductDto> genericProductDtos = productPage.stream().map(product -> ProductToGenericProductDto.convert(product)).toList();
        Page<GenericProductDto> genericProductDtoPage = new
                PageImpl<GenericProductDto>(genericProductDtos, productPage.getPageable() ,
                productPage.getTotalElements());



        return genericProductDtoPage;
    }
}
