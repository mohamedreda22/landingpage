package com.moaaz.modernhome.Order;

import com.moaaz.modernhome.Cart.ProductCart;
import com.moaaz.modernhome.Cart.ProductCartResponse;
import com.moaaz.modernhome.User.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {
    private long id;


    private List<ProductCartResponse> productCartResponses;

    private LocalDate creationDate;


    private String name;
    private String email;
    private String phone1;
    private String phone2;
    private String address;

    private double total;

    private OrderStatus status;

    public static OrderResponse convertOrderToOrderResponse(Order order) {
        return OrderResponse
                .builder()
                .id(order.getId())
                .name(order.getUser().getName())
                .phone1(order.getUser().getPhone1())
                .phone2(order.getUser().getPhone2())
                .address(order.getUser().getAddress())
                .email(order.getUser().getEmail())
                .productCartResponses(order.getProductCarts().stream().map(ProductCartResponse::convertProductCartToProductCartResponse).toList())
                .creationDate(order.getLocalDate())
                .total(order.getTotal())
                .status(order.getStatus())
                .build();
    }

}
