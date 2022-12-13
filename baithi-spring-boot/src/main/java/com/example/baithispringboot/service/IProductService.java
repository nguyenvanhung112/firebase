package com.example.baithispringboot.service;

import com.example.baithispringboot.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void save(Product product);
}
