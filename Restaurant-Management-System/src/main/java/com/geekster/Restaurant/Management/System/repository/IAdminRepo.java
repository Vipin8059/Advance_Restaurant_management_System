package com.geekster.Restaurant.Management.System.repository;

import com.geekster.Restaurant.Management.System.model.Admin;
import com.geekster.Restaurant.Management.System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin,Long> {

    Admin findFirstByAdminEmail(String newEmail);
}
