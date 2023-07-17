package com.geekster.Restaurant.Management.System.service;

import com.geekster.Restaurant.Management.System.model.Order;
import com.geekster.Restaurant.Management.System.model.User;
import com.geekster.Restaurant.Management.System.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    IOrderRepo orderRepo;
    public String orderFood(Order order) {
        orderRepo.save(order);
        return "order placed successfully";
    }
}
