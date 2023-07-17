package com.geekster.Restaurant.Management.System.service;

import com.geekster.Restaurant.Management.System.model.AuthenticationToken;
import com.geekster.Restaurant.Management.System.model.Enum.UserType;
import com.geekster.Restaurant.Management.System.model.Order;
import com.geekster.Restaurant.Management.System.model.User;
import com.geekster.Restaurant.Management.System.model.dto.SignInInput;
import com.geekster.Restaurant.Management.System.model.dto.SignUpOutput;
import com.geekster.Restaurant.Management.System.repository.IUserRepo;
import com.geekster.Restaurant.Management.System.service.utility.emailUtility.EmailHandler;
import com.geekster.Restaurant.Management.System.service.utility.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    AdminService adminService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    FoodItemService foodItemService;

    @Autowired
    OrderService orderService;

    //User signUp

    public SignUpOutput signUpUser(User user) {
        Boolean signUpStatus = true;
        String signUpStatusMessage =null;

        String newEmail = user.getUserEmail();
        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        UserType userType = user.getUserType();

        if(userType.equals(UserType.VISITOR)){
            signUpStatusMessage = "Invalid userType";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        else if(userType.equals(UserType.ADMIN)){
            signUpStatusMessage = "Invalid userType";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        User existingUser = userRepo.findFirstByUserEmail(newEmail);
        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        try{
                String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());
                user.setUserPassword(encryptedPassword);
                userRepo.save(user);
                return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch (Exception e){
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

    }

    public String signInUser(String email,String password) {
        String signInStatusMessage = null;
            User existingUser = userRepo.findFirstByUserEmail(email);
            if(existingUser == null)
            {
                signInStatusMessage = "Email not registered!!!";
                return signInStatusMessage;

            }
            try {
                String encryptedPassword = PasswordEncrypter.encryptPassword(password);
                if(existingUser.getUserPassword().equals(encryptedPassword))
                {
                    //session should be created since password matched and user id is valid
                    AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                    authenticationService.saveAuthToken(authToken);

                    EmailHandler.sendEmail(email,"email testing",authToken.getTokenValue());
                    return "Token sent to your email";
                }
                else {
                    signInStatusMessage = "Invalid credentials!!!";
                    return signInStatusMessage;
                }
            }
            catch(Exception e)
            {
                signInStatusMessage = "Internal error occurred during sign in";
                return signInStatusMessage;
            }
    }

    public String signOutUser(String email) {
        User user = userRepo.findFirstByUserEmail(email);
        AuthenticationToken token = authenticationService.findFirstByUser(user);
        authenticationService.removeToken(token);
        return "User Signed out successfully";
    }

    public String orderFood(Order order, String email, String token) {
        if(authenticationService.authenticateUser(email, token)){
            User customer = userRepo.findFirstByUserEmail(email);
            order.setUser(customer);
            return foodItemService.orderFood(order);
        }
        else{
            return "unauthorized user to place order";
        }
    }
}
