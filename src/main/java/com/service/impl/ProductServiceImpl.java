package com.service.impl;

import com.entity.Product;
import com.repository.ProductRepository;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(productRepository.getOne(id));
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void update(Product newProduct) {
        productRepository.saveAndFlush(newProduct);
    }

    @Override
    public void add(Product product) {
        productRepository.save(product);
    }
}
