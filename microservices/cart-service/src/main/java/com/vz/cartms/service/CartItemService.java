package com.vz.cartms.service;

import com.vz.cartms.dto.Product;
import com.vz.cartms.model.CartItem;
import com.vz.cartms.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CartItemRepository cartRepo;

    public CartItem addItemToCart(int productId, int quantity) {

        Optional<CartItem> availableItem = cartRepo.findByProductId(productId);
        if(availableItem.isPresent()){
            CartItem item = availableItem.get();
            int oldQuantity = item.getQuantity();
            int newQuantity = oldQuantity+quantity;
            float oldPrice = item.getPrice()/oldQuantity;
            item.setQuantity(newQuantity);
            item.setPrice(newQuantity*oldPrice);
            return cartRepo.save(item);
        }

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
      Product product =  restTemplate.getForObject("http://PRODUCT-SERVICE/api/products/"+productId,
                            Product.class);

        return product;

    }

}
