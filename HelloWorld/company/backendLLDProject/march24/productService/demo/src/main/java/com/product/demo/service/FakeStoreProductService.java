package com.product.demo.service;

import com.product.demo.Exception.NotFoundException;
import com.product.demo.dtos.FakeStoreProductDto;
import com.product.demo.dtos.GenericProductDto;
import com.product.demo.mapper.FakeStoreDtoToGenericProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    @Value("https://fakestoreapi.com")
    private String fakeStoreBaseUrl;
    @Value("/products")
    private String productUrl;
    // we are using RestTemplate builder as we don't have Bean for restTemplate.
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException{
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(fakeStoreBaseUrl+productUrl+"/{id}",
                FakeStoreProductDto.class,
                id);
        if(responseEntity.getBody()!=null){
            return FakeStoreDtoToGenericProductDto.convert(responseEntity.getBody());
        } else {
            throw new NotFoundException("Product with ID "+id+" was not found");
        }
    }


    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(fakeStoreBaseUrl+productUrl ,
                genericProductDto,
                FakeStoreProductDto.class);
        return FakeStoreDtoToGenericProductDto.convert(responseEntity.getBody());
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(UUID id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(fakeStoreBaseUrl+productUrl+"/"+id,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class,
                id);
        if(responseEntity.getBody()!=null){
        return FakeStoreDtoToGenericProductDto.convert(responseEntity.getBody());
        }
        throw new NotFoundException("Product with ID "+id+" was not found");
    }

    @Override
    public GenericProductDto updateProduct(UUID id, GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public List<GenericProductDto> getProductsLimit(Long limit) {
        return null;
    }
}
