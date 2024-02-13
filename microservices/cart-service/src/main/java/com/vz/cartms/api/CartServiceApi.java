package com.vz.cartms.api;

import com.vz.cartms.model.CartItem;
import com.vz.cartms.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartServiceApi {

    @Autowired
    private CartItemService cartService;

    @PostMapping("/id/{productId}/quantity/{quantity}")
    public CartItem addToCart(@PathVariable int productId,
                              @PathVariable int quantity){
        return cartService.addItemToCart(productId,quantity);
    }

    @GetMapping
    public List<CartItem> getAllItems(){
        return cartService.getAllItems();
    }

}
