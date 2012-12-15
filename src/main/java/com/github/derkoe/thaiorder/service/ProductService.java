package com.github.derkoe.thaiorder.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.derkoe.thaiorder.model.Product;
import com.github.derkoe.thaiorder.model.ProductRepository;

@Service
public class ProductService
{
    @Inject
    private ProductRepository productRepository;

    public Iterable<Product> getProducts()
    {
        return productRepository.findAll();
    }
}
