package com.example.baithispringboot.controller;

import com.example.baithispringboot.model.Product;
import com.example.baithispringboot.model.Ticket;
import com.example.baithispringboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getList() {
        List<Product> productList = productService.findAll();
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
