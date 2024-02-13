package com.vz.productms.api;

import com.vz.productms.model.Product;
import com.vz.productms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductApi {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable int productId) {
        return productService.getProduct(productId);
    }

    @ResponseStatus(code=HttpStatus.ACCEPTED)
    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/name/{productName}")
    public Product getProductByName(@PathVariable String productName) {
        return productService.getProductByName(productName);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

}
