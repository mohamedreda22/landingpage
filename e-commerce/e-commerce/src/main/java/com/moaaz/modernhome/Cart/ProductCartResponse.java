package com.moaaz.modernhome.Cart;

import com.moaaz.modernhome.Product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCartResponse {

    private long id;
    private ProductResponse productResponse;
    private int quantity;
    private LocalDate additionDate;

    public static ProductCartResponse convertProductCartToProductCartResponse(
            ProductCart productCart
    ){
        return ProductCartResponse
                .builder()
                .id(productCart.getId())
                .productResponse(ProductResponse.convertProductToProductResponse(productCart.getProduct()))
                .quantity(productCart.getQuantity())
                .additionDate(productCart.getAdditionDate())
                .build();
    }
}
