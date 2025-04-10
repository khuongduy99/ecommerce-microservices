package com.ms.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.productservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
