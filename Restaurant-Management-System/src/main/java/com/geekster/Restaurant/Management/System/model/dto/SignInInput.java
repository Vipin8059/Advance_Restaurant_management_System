package com.geekster.Restaurant.Management.System.model.dto;

import com.geekster.Restaurant.Management.System.model.Enum.UserType;
import com.geekster.Restaurant.Management.System.model.User;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInInput {

    private String email;
    private String password;

    private UserType userType;
}
