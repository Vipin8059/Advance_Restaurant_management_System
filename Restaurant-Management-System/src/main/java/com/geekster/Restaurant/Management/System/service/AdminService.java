package com.geekster.Restaurant.Management.System.service;

import com.geekster.Restaurant.Management.System.model.Admin;
import com.geekster.Restaurant.Management.System.model.AuthenticationToken;
import com.geekster.Restaurant.Management.System.model.Enum.UserType;
import com.geekster.Restaurant.Management.System.model.FoodItem;
import com.geekster.Restaurant.Management.System.model.User;
import com.geekster.Restaurant.Management.System.model.dto.SignUpOutput;
import com.geekster.Restaurant.Management.System.repository.IAdminRepo;
import com.geekster.Restaurant.Management.System.service.utility.emailUtility.EmailHandler;
import com.geekster.Restaurant.Management.System.service.utility.hashingUtility.PasswordEncrypter;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    FoodItemService foodItemService;

    public SignUpOutput signUpAdmin(Admin admin) {
        Boolean signUpStatus = true;
        String signUpStatusMessage =null;

        String newEmail = admin.getAdminEmail();
        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        Admin existingAdmin = adminRepo.findFirstByAdminEmail(newEmail);
        if(existingAdmin != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        try{
            String encryptedPassword = PasswordEncrypter.encryptPassword(admin.getAdminPassword());
            admin.setAdminPassword(encryptedPassword);
            adminRepo.save(admin);
            return new SignUpOutput(signUpStatus, "Admin registered successfully!!!");
        }
        catch (Exception e){
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

    }

    public String signInAdmin(String email, String password) {
        String signInStatusMessage = null;
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);
        if(existingAdmin == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(password);
            if(existingAdmin.getAdminPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingAdmin);
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


    public String addFoodItem(FoodItem foodItem, String email) {
        Admin foodItemOwner = adminRepo.findFirstByAdminEmail(email);
        foodItem.setAdmin(foodItemOwner);
        return foodItemService.addFoodItem(foodItem);
    }

    public String removeFoodItem(Long foodItemId, String email) {
        Admin admin = adminRepo.findFirstByAdminEmail(email);
        return foodItemService.removeFoodItem(foodItemId);
    }
}
