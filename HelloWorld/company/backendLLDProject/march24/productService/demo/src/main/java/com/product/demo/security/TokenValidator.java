package com.product.demo.security;
import com.product.demo.dtos.ValidateTokenRequestDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@Service
public class TokenValidator {

        private RestTemplateBuilder restTemplateBuilder;
        public TokenValidator(RestTemplateBuilder restTemplateBuilder) {
            this.restTemplateBuilder = restTemplateBuilder;
        }
        public JwtData validateToken(ValidateTokenRequestDto validateTokenRequestDto) {
            String userAuthServiceUrl = "http://localhost:9000/auth/validate";
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<JwtData> response = restTemplate.postForEntity(userAuthServiceUrl, validateTokenRequestDto, JwtData.class);
            return response.getBody();
        }
}
