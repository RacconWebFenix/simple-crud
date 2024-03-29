package com.raccon.simplecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raccon.simplecrud.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
