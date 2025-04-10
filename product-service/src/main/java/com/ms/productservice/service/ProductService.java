package com.ms.productservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ms.productservice.dto.ProductRequest;
import com.ms.productservice.dto.ProductResponse;
import com.ms.productservice.entity.Product;
import com.ms.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository repository;

  public List<ProductResponse> findAll() {
    return repository.findAll()
        .stream()
        .map(this::mapToResponse)
        .toList();
  }

  public void create(ProductRequest request) {
    Product product = Product.builder()
        .name(request.name())
        .description(request.description())
        .price(request.price())
        .quantity(request.quantity())
        .createdAt(LocalDateTime.now())
        .build();

    repository.save(product);
  }

  private ProductResponse mapToResponse(Product product) {
    return new ProductResponse(
        product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice(),
        product.getQuantity()
    );
  }
}
