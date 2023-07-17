package com.geekster.Restaurant.Management.System.model;

import com.geekster.Restaurant.Management.System.model.Enum.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    @Pattern(regexp = "^(?!.*@admin\\.com$)[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    private String userEmail;

    private String userPassword;

    @Enumerated(value = EnumType.STRING)
    private UserType userType;
}
