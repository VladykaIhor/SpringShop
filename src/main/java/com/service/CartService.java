package com.service;

import com.entity.Cart;
import com.entity.Product;
import com.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CartService {

    void addProductToCart(Cart cart, Product product);

    List<Product> getCartProducts(Cart cart);

    Cart createCart(Cart cart);

    Optional<Cart> getLastCartByUser(User user);

    int getSizeOfACart(Cart cart);

}
