package com.service.impl;

import com.entity.Cart;
import com.entity.Product;
import com.entity.User;
import com.repository.CartJpaRepository;
import com.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartJpaRepository cartJpaRepository;

    @Autowired
    public CartServiceImpl(CartJpaRepository cartJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
    }

    @Transactional
    @Override
    public Cart createCart(Cart cart) {
        cartJpaRepository.save(cart);
        return cart;
    }

    @Transactional
    @Override
    public void addProductToCart(Cart cart, Product product) {
        cart.getProducts().add(product);
        cartJpaRepository.saveAndFlush(cart);
    }

    @Transactional
    @Override
    public List<Product> getCartProducts(Cart cart) {
        return cart.getProducts();
    }

    @Transactional
    @Override
    public Optional<Cart> getLastCartByUser(User user) {
        return cartJpaRepository.findFirstByUserOrderByIdDesc(user);
    }

    @Transactional
    @Override
    public int getSizeOfACart(Cart cart) {
        return cart.getSizeOfCart();
    }
}
