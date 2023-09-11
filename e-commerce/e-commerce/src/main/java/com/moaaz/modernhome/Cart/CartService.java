package com.moaaz.modernhome.Cart;

import com.moaaz.modernhome.Product.Product;
import com.moaaz.modernhome.Product.ProductResponse;
import com.moaaz.modernhome.Product.ProductService;

import com.moaaz.modernhome.User.User;
import com.moaaz.modernhome.User.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    public Cart createCart(User user ) {
        Cart cart= new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    public CartResponse addToCartWithNewFeature(long cartId, long productId) {
        Cart cart = getCartById(cartId);
        Product sendingProduct = productService.getProductById(productId);
        // check if product exist before ==> we will just add his quantity
        List<ProductCart> productCarts = cart.getProductCarts();
        boolean isExist = false;
        for (int i = 0; i < productCarts.size(); i++) {
            // this product are existing before in the cart so we will just add the quantity
            if (sendingProduct.getId() == productCarts.get(i).getProduct().getId()) {
                productCarts.get(i).setQuantity(productCarts.get(i).getQuantity() + 1);// add quantity with one.
                log.info("Product Are Exist Before");
                isExist = true;
                break;
            }
        }
        // product not are in our cart so adding it for our cart.
        if (!isExist) {
            log.info("Product Aren't Exist Before");
            ProductCart productCart = ProductCart
                    .builder()
                    .quantity(1)
                    .additionDate(LocalDate.now())
                    .product(sendingProduct)
                    .build();
//            productCart = productCartRepository.save(productCart);
            productCarts.add(productCart);
            cart.setProductCarts(productCarts);
        }
        cart.setTotal(cart.calcTotal());
        cart = cartRepository.save(cart);
        return CartResponse
                .builder()
                .id(cart.getId())
                .productCartResponses(cart.getProductCarts().stream().map(productcart -> ProductCartResponse.convertProductCartToProductCartResponse(productcart)).toList())
//                .productResponses(cart.getProductCarts().stream().map(this::convertProductCartToProductResponse).toList())
                .total(cart.getTotal())
                .build();
    }

    public CartResponse deleteFromCartWithNewFeature(long cartId, long productId) {
        Cart cart = getCartById(cartId);
        Product product = productService.getProductById(productId);
        List<ProductCart> productCarts = cart.getProductCarts();
        for (ProductCart productCart : productCarts) {
            log.info("This Is Product Cart Id {}", productCart.getProduct().getId());
            log.info("And This Is Product Id To Delete From The Cart {}", productId);
            if (productCart.getProduct().getId() == productId) {
                productCarts.remove(productCart);
                log.info("Product Are Deleted From Cart Successfully");
                break;
            }
        }
//        cart.setProductCarts(productCarts);
        cart.setTotal(cart.getTotal());
        cart = cartRepository.save(cart);
        return CartResponse.convertCartToCartResponse(cart);
    }

    public CartResponse updateCart(Cart cart) {
        getCartById(cart.getId());
        cart.setTotal(cart.calcTotal());
        cart = cartRepository.save(cart);
        return CartResponse.convertCartToCartResponse(cart);
    }

    private Cart getCartById(long cartId) {
        return cartRepository.findById(cartId).orElseThrow(
                () -> new NoSuchElementException("There Are No Cart With Id = " + cartId)
        );
    }


//    public ProductResponse convertProductCartToProductResponse(ProductCart productCart) {
//        return ProductResponse.builder()
//                .name(productCart.getProduct().getName())
//                .details(productCart.getProduct().getDetails())
//                .price(productCart.getProduct().getPrice())
//                .discount(productCart.getProduct().getDiscount())
//                .total(productCart.getProduct().getTotal())
//                .creationDate(productCart.getProduct().getCreationDate())
//                .categoryName(productCart.getProduct().getCategory().getName())
//                .build();
//    }
//    public Cart addToCart(long productId, String userEmail) {
//
//        User user = userService.getUserByEmailWithoutException(userEmail);
//        // if user are not exist we will create user with this email.
//        if (user == null)
//            user = userService.addUserByEmail(userEmail);
//
//        Product sendingProduct = productService.getProductById(productId);
//        Cart cart = user.getCart();
//
//        // check if product exist before ==> we will just add his quantity
//        List<ProductCart> productCarts = cart.getProductCarts();
//        boolean isExist = false;
//        for (int i = 0; i < productCarts.size(); i++) {
//            // this product are existing before in the cart so we will just add the quantity
//            if (sendingProduct.getId() == productCarts.get(i).getProduct().getId()) {
//                productCarts.get(i).setQuantity(productCarts.get(i).getQuantity() + 1);// add quantity with one.
//                isExist = true;
//                break;
//            }
//        }
//        // product not are in our cart so adding it for our cart.
//        if (!isExist) {
//            ProductCart productCart = ProductCart
//                    .builder()
//                    .quantity(1)
//                    .additionDate(LocalDate.now())
//                    .product(sendingProduct)
//                    .build();
//            cart.getProductCarts().add(productCart);
//        }
//        cart.setTotal(cart.calcTotal());
//        return cartRepository.save(cart);
//    }
//
//    public Cart deleteFromCart(String userEmail, long productId) {
//        User user = userService.getUserByEmail(userEmail);
//        Product product = productService.getProductById(productId);
//        Cart cart = user.getCart();
//        List<ProductCart> productCarts = cart.getProductCarts();
//        for (ProductCart productCart : productCarts) {
//            if (productCart.getProduct().getId() == productId) {
//                productCarts.remove(productCart);
//                break;
//
//            }
//        }
//        cart.setTotal(cart.calcTotal());
//        return cartRepository.save(cart);
//    }


}
