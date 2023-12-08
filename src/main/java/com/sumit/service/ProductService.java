package com.sumit.service;

import com.sumit.entity.Product;
import com.sumit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static List<Product> productList = null;

    // Static Initialization Block to initialize the product list
    static {
        productList = IntStream
                .rangeClosed(1, 100)
                .mapToObj(e -> new Product(e, "Name-"+e, new Random().nextInt(10), new Random().nextFloat(100.0F) ))
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(Integer id) {
        Optional<Product> person = productList.stream().filter(p -> p.getId().equals(id)).findFirst();
        return person.orElse(null);
    }
}