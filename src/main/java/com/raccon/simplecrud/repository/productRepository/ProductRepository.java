package com.raccon.simplecrud.repository.productRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raccon.simplecrud.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
