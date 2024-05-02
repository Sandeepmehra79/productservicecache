package com.product.demo.repository;


import com.product.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    public Optional<Product> getProductsById(UUID uuuid);
    public Product save(Product product);
    public List<Product> findAll();
    public void deleteById(UUID uuid);
    public Page<Product> findAll(Pageable pageable);


    Page<Product> findAllByTitle(String title,Pageable pageable);

    Page<Product> findAllByTitleContaining(String title,Pageable pageable);

}
