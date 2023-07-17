package com.geekster.Restaurant.Management.System.repository;

import com.geekster.Restaurant.Management.System.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodItemRepo extends JpaRepository<FoodItem,Long> {
}
