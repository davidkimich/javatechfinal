package com.csis231.api.service;

import com.csis231.api.exception.ResourceNotFoundException;
import com.csis231.api.model.product;
import com.csis231.api.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class productService {

    private final productRepository productRepository;

    @Autowired
    public productService(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<product> getAllProducts() {
        return productRepository.findAll();
    }

    public product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public product createProduct(product product) {
        return productRepository.save(product);
    }

    public product updateProduct(Long id, product productDetails) {
        product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id : " + id));


        product.setProductName(productDetails.getProductName());
        // product.setProductImage(productDetails.getProductImage());
        product.setProductDescription(productDetails.getProductDescription());
        product.setProductPrice(productDetails.getProductPrice());

        return productRepository.save(product);
    }

    public Map<String, Boolean> deleteProduct(Long id) {
        product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        productRepository.delete(product);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
