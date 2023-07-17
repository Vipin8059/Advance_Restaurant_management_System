package com.geekster.Restaurant.Management.System.controller;

import com.geekster.Restaurant.Management.System.model.Admin;
import com.geekster.Restaurant.Management.System.model.FoodItem;
import com.geekster.Restaurant.Management.System.model.User;
import com.geekster.Restaurant.Management.System.model.dto.SignUpOutput;
import com.geekster.Restaurant.Management.System.service.AdminService;
import com.geekster.Restaurant.Management.System.service.AuthenticationService;
import com.geekster.Restaurant.Management.System.service.FoodItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    FoodItemService foodItemService;

    @PostMapping("admin/signUp")
    public SignUpOutput signUp(@RequestBody @Valid Admin admin){
        return adminService.signUpAdmin(admin);
    }

    @PostMapping("foodItem")
    public String addFoodItem(@RequestBody @Valid FoodItem foodItem, @RequestParam String email, @RequestParam String token){
        if(authenticationService.authenticateAdmin(email,token)){
            return adminService.addFoodItem(foodItem,email);

        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @DeleteMapping("foodItem")
    public String deleteFoodItem(@RequestParam Long foodItemId, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationService.authenticateAdmin(email,token)) {
            return adminService.removeFoodItem(foodItemId,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }
}
