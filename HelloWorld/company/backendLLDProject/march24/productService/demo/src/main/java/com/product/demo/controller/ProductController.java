package com.product.demo.controller;

import com.product.demo.Exception.LimitTooLargeException;
import com.product.demo.Exception.NoProductInListException;
import com.product.demo.Exception.NotFoundException;
import com.product.demo.dtos.FakeStoreProductDto;
import com.product.demo.dtos.GenericProductDto;
import com.product.demo.dtos.ValidateTokenRequestDto;
import com.product.demo.mapper.GenericProductDtoToProduct;
import com.product.demo.mapper.ProductToGenericProductDto;
import com.product.demo.model.Product;
import com.product.demo.security.JwtData;
import com.product.demo.security.TokenValidator;
import com.product.demo.service.FakeStoreProductService;
import com.product.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.validation.annotation.ValidationAnnotationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
// adding roles for authorization https://stackoverflow.com/questions/58205510/spring-security-mapping-oauth2-claims-with-roles-to-secure-resource-server-endp

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final TokenValidator tokenValidator;
    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService, TokenValidator tokenValidator){
        this.productService = productService;
        this.tokenValidator = tokenValidator;
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @RequestHeader("X-User-ID") Long userId, @PathVariable UUID id) throws NotFoundException, UserPrincipalNotFoundException {
        ValidateTokenRequestDto validateTokenRequestDto = new ValidateTokenRequestDto();
        validateTokenRequestDto.setUserId(userId);
        validateTokenRequestDto.setToken(authToken);
        JwtData jwtData =  tokenValidator.validateToken(validateTokenRequestDto);
        if(jwtData.getRoles()==null || jwtData.getRoles().isEmpty()){
            throw new UserPrincipalNotFoundException("the use not found");
        }
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProduct(@PathVariable UUID id) throws NotFoundException {
        return productService.deleteProduct(id);
    }

    @PostMapping
    public ResponseEntity<GenericProductDto> createProduct(@RequestBody GenericProductDto genericProductDto){
        return ResponseEntity.ok(productService.createProduct(genericProductDto));
    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete/{id}")
    public GenericProductDto deleteProductById(@PathVariable UUID id) throws NotFoundException {
        return productService.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public GenericProductDto updateProductById(@PathVariable UUID id, @RequestBody GenericProductDto genericProductDto) throws NotFoundException {
        return productService.updateProduct(id , genericProductDto);
    }

    @GetMapping("/limit/{in}")
    public List<GenericProductDto> getProductByLimit(@PathVariable Long in) throws LimitTooLargeException, NoProductInListException {
        return productService.getProductsLimit(in);
    }

    // ArrayList declared with String type parameter

// Add an Integer (type mismatch at runtime, not compile time)
}
