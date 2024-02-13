package com.vz.productms.service;

import com.vz.productms.model.Product;
import com.vz.productms.repositrory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }

    public Product getProductByName(String productName) {
        return productRepository.findByProductName(productName).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }




}
