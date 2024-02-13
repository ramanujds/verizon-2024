package com.vz.cartms.repository;

import com.vz.cartms.dto.Product;
import com.vz.cartms.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {


   Optional<CartItem> findByProductId(int productId);

}
