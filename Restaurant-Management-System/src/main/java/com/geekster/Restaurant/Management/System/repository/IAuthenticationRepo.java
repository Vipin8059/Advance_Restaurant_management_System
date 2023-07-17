package com.geekster.Restaurant.Management.System.repository;

import com.geekster.Restaurant.Management.System.model.AuthenticationToken;
import com.geekster.Restaurant.Management.System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findFirstByTokenValue(String token);

    AuthenticationToken findFirstByUser(User user);
}
