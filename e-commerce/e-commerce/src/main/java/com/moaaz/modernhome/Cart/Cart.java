package com.moaaz.modernhome.Cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moaaz.modernhome.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @OneToOne
    @JsonIgnore
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<ProductCart>productCarts;

    private double total;




    public double calcTotal(){
        double result = 0;
        for(ProductCart productCart : this.productCarts)
            result += productCart.getQuantity() * productCart.getProduct().getTotal();
        this.total=result;
        return total;
    }





}
