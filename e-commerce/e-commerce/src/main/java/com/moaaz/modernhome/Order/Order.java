package com.moaaz.modernhome.Order;

import com.moaaz.modernhome.Cart.ProductCart;
import com.moaaz.modernhome.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    private List<ProductCart> productCarts;

    private LocalDate localDate;


    @ManyToOne
    private User user;

    private double total;


    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
