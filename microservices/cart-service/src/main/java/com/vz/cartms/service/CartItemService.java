package com.vz.cartms.service;

import com.vz.cartms.dto.Product;
import com.vz.cartms.model.CartItem;
import com.vz.cartms.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CartItemRepository cartRepo;

    public CartItem addItemToCart(int productId, int quantity) {
        Product product = getProductDetails(productId);
        float total = product.price() * quantity;
        CartItem item = new CartItem();
        item.setPrice(total);
        item.setProductId(productId);
        item.setQuantity(quantity);
        return cartRepo.save(item);
    }

    public List<CartItem> getAllItems(){
        return cartRepo.findAll();
    }

    private Product getProductDetails(int productId) {
      Product product =  restTemplate.getForObject("http://localhost:5100/api/products/"+productId,
                            Product.class);

        return product;

    }

}
