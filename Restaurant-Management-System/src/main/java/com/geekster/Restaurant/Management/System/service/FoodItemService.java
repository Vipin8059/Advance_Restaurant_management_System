package com.geekster.Restaurant.Management.System.service;

import com.geekster.Restaurant.Management.System.model.FoodItem;
import com.geekster.Restaurant.Management.System.model.Order;
import com.geekster.Restaurant.Management.System.repository.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
    @Autowired
    IFoodItemRepo foodItemRepo;

    @Autowired
    OrderService orderService;


    public String addFoodItem(FoodItem foodItem) {
    foodItemRepo.save(foodItem);
    return "food item added successfully";

    }

    public String removeFoodItem(Long foodItemId) {
        if(foodItemRepo.existsById(foodItemId)){
            foodItemRepo.deleteById(foodItemId);
            return "food item removed successfully";
        }
        else{
            return "food item not found for id "+foodItemId;
        }

    }

    public String orderFood(Order order) {
        List<FoodItem> foodItems = order.getFoodItem();
        for(FoodItem ele:foodItems){
            if(!foodItemRepo.existsById(ele.getFoodItemId())){
                return "not a valid food item";
            }
        }
        return orderService.orderFood(order);
    }
}
