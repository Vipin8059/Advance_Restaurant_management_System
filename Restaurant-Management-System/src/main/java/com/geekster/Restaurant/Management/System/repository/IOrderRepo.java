package com.geekster.Restaurant.Management.System.repository;

import com.geekster.Restaurant.Management.System.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepo extends JpaRepository<Order,Long> {
}
