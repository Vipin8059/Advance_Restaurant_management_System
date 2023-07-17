package com.geekster.Restaurant.Management.System.controller;

import com.geekster.Restaurant.Management.System.model.Enum.UserType;
import com.geekster.Restaurant.Management.System.model.Order;
import com.geekster.Restaurant.Management.System.model.User;
import com.geekster.Restaurant.Management.System.model.dto.SignInInput;
import com.geekster.Restaurant.Management.System.model.dto.SignUpOutput;
import com.geekster.Restaurant.Management.System.service.AdminService;
import com.geekster.Restaurant.Management.System.service.AuthenticationService;
import com.geekster.Restaurant.Management.System.service.OrderService;
import com.geekster.Restaurant.Management.System.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;


    @Autowired
    AdminService adminService;

    @Autowired
    OrderService orderService;
    //sign up user
    @PostMapping("user/signUp")
    public SignUpOutput signUp(@RequestBody @Valid User user){
        return userService.signUpUser(user);
    }

    @PostMapping("signIn")
    public String signInUser(@RequestBody @Valid SignInInput signInInput){
        UserType userType = signInInput.getUserType();
        String email = signInInput.getEmail();
        String password = signInInput.getPassword();
        if(userType.equals(UserType.ADMIN)){
            return adminService.signInAdmin( email, password);
        }
        else if (userType.equals(UserType.VISITOR)) {
            return "you didn't need to sign in";
        }
        else  return userService.signInUser(email, password);
    }

    @DeleteMapping("user/signOut")
    public String sigOutUser(@RequestParam String email,@RequestParam String token)
    {
        if(authenticationService.authenticateUser(email,token)) {
            return userService.signOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }
    }

    @PostMapping("order")
    public String orderFood(@RequestBody Order order, @RequestParam String email, @RequestParam String token){
        return userService.orderFood(order,email,token);
    }



}
