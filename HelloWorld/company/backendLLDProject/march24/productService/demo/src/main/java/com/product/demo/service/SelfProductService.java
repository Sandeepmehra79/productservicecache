package com.product.demo.service;

import com.product.demo.Exception.LimitTooLargeException;
import com.product.demo.Exception.NoProductInListException;
import com.product.demo.Exception.NotFoundException;
import com.product.demo.dtos.GenericProductDto;
import com.product.demo.mapper.GenericProductDtoToProduct;
import com.product.demo.mapper.ProductToGenericProductDto;
import com.product.demo.model.Product;
import com.product.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public GenericProductDto getProductById(UUID id) throws NotFoundException {
        if (productRepository.getProductsById(id).isPresent()){
            Product product = productRepository.getProductsById(id).get();
            System.out.println(product.getTitle());

            return ProductToGenericProductDto.convert(productRepository.getReferenceById(id));
        }
        throw new NotFoundException("product with id "+id +" is not found");
    }



    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = productRepository.save(GenericProductDtoToProduct.convert(genericProductDto));
        System.out.println(product.getId());
        return ProductToGenericProductDto.convert(product);
    }

    @Override
    public ArrayList<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        ArrayList<GenericProductDto> genericProducts = new ArrayList<>();
        for(Product p : products){
            genericProducts.add(ProductToGenericProductDto.convert(p));
        }
        return genericProducts;
    }

    @Override
    public GenericProductDto deleteProduct(UUID id) throws NotFoundException {
        if(productRepository.getProductsById(id).isPresent()){
            Product product = productRepository.getProductsById(id).get();
            productRepository.deleteById(id);
            return ProductToGenericProductDto.convert(product);
        }
        throw new NotFoundException("product with id "+id +" is not found");
    }

    @Override
    public GenericProductDto updateProduct(UUID id, GenericProductDto genericProductDto) throws NotFoundException {
        if(productRepository.getProductsById(id).isPresent()){
            Product product = GenericProductDtoToProduct.convert(genericProductDto);
            product.setId(id);
            productRepository.save(product);
            GenericProductDto genericProductDtoOut = ProductToGenericProductDto.convert(productRepository.getProductsById(id).get());
            return genericProductDtoOut;
        }
        throw new NotFoundException("product with id "+id +" is not found");
    }

    @Override
    public List<GenericProductDto> getProductsLimit(Long limit) throws NoProductInListException, LimitTooLargeException {
        int myInt;
        try {
            myInt = Math.toIntExact(limit);
            Pageable pageable = PageRequest.of(0, myInt);
        List<Product> products = productRepository.findAll(pageable).getContent();
        ArrayList<GenericProductDto> genericProductDtos = new ArrayList<>();
        for(Product p : products){
            genericProductDtos.add(ProductToGenericProductDto.convert(p));
        }
        return genericProductDtos;
        } catch (ArithmeticException e) {
            throw new LimitTooLargeException("Value too large to be converted to int");
        }
    }
}
