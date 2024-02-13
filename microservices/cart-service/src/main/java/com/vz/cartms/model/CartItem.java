package com.vz.cartms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;
    private int productId;

    private int quantity;

    private float price;


}
