package com.moaaz.modernhome.Cart;

import com.moaaz.modernhome.Product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {

    private long id;
    private List<ProductCartResponse>productCartResponses;
    private String userEmail;
    private double total;

    public static CartResponse convertCartToCartResponse(Cart cart){
        return CartResponse
                .builder()
                .id(cart.getId())
                .userEmail(cart.getUser().getEmail())
                .productCartResponses(cart.getProductCarts().stream().map(productCart -> ProductCartResponse.convertProductCartToProductCartResponse(productCart)).toList())
                .total(cart.getTotal())
                .build();
    }
}
