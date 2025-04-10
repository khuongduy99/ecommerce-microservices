package com.ms.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.productservice.dto.ProductRequest;
import com.ms.productservice.dto.ProductResponse;
import com.ms.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;

  @GetMapping
  public List<ProductResponse> getAll() {
    return service.findAll();
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody ProductRequest request) {
    service.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
