package com.geekster.Restaurant.Management.System.service;

import com.geekster.Restaurant.Management.System.model.AuthenticationToken;
import com.geekster.Restaurant.Management.System.model.User;
import com.geekster.Restaurant.Management.System.repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepo authenticationRepo;
    public void saveAuthToken(AuthenticationToken authToken) {
    authenticationRepo.save(authToken);
    }

    public boolean authenticateUser(String email, String token) {
        AuthenticationToken authToken = authenticationRepo.findFirstByTokenValue(token);

        if(authToken == null)
        {
            return false;
        }

        String tokenConnectedEmail = authToken.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }

    public AuthenticationToken findFirstByUser(User user) {
        return authenticationRepo.findFirstByUser(user);
    }

    public void removeToken(AuthenticationToken token) {
        authenticationRepo.delete(token);
    }

    public boolean authenticateAdmin(String email, String token) {
        AuthenticationToken authToken = authenticationRepo.findFirstByTokenValue(token);

        if(authToken == null)
        {
            return false;
        }
        String tokenConnectedEmail = authToken.getAdmin().getAdminEmail();
        return tokenConnectedEmail.equals(email);
    }
}
