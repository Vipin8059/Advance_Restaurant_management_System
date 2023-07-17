package com.geekster.Restaurant.Management.System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodItemId;

    private String foodTitle;

    private String foodDescription;

    @ManyToOne
    @JoinColumn(name = "fk_admin_id")
    private Admin admin;
}
