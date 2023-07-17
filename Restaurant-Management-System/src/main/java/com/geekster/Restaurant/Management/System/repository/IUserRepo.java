package com.geekster.Restaurant.Management.System.repository;

import com.geekster.Restaurant.Management.System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.UnknownServiceException;

public interface IUserRepo extends JpaRepository<User,Long> {
    User findFirstByUserEmail(String newEmail);
}
