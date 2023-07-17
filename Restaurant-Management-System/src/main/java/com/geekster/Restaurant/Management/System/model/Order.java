package com.geekster.Restaurant.Management.System.model;

import com.geekster.Restaurant.Management.System.model.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FoodOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Integer orderQuantity;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    //mappings
    @OneToOne
    @JoinColumn(name = "fk_User_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "fk_FoodItem_id")
    private List<FoodItem> foodItem;


}
