package com.service.impl;

import com.entity.Order;
import com.entity.User;
import com.repository.OrderRepository;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Long getOrderIdByUser(User user) {
        return user.getId();
    }
}
