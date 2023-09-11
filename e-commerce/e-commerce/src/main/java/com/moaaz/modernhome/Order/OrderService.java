package com.moaaz.modernhome.Order;

import com.moaaz.modernhome.Cart.Cart;
import com.moaaz.modernhome.Cart.ProductCart;
import com.moaaz.modernhome.User.User;
import com.moaaz.modernhome.User.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    public void addOrder(long userId) {
        User user = userService.getUserById(userId);
        List<ProductCart> productCarts = user.getCart().getProductCarts();
        user.getCart().setProductCarts(new ArrayList<>());
        Order order = new Order();
        order.setProductCarts(productCarts);
        order.setTotal(user.getCart().getTotal());
        order.setLocalDate(LocalDate.now());
        order.setUser(user);
        order.setStatus(OrderStatus.IN_WAITING);
        orderRepository.save(order);

        log.info("this is a order with user email equal to {}", order.getUser().getEmail());

        // make user cart is empty.
        Cart cart = user.getCart();
        cart.setProductCarts(new ArrayList<>());
        cart.setTotal(0);
        user.setCart(cart);
        userService.updateUser(user, user.getId());

    }

    // Make Order In Delivery Status
    public void acceptOrder(long orderId) {
        Order order = getOrderById(orderId);
        if (order.getStatus() == OrderStatus.IN_WAITING)
            order.setStatus(OrderStatus.IN_DELIVERY);
        orderRepository.save(order);
    }

    // Make Order Completed
    public void completeOrder(long orderId) {
        Order order = getOrderById(orderId);
        if (order.getStatus() == OrderStatus.IN_DELIVERY)
            order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
    }


    // get all completed orders from date to date that have status
    public List<OrderResponse> getAllOrdersFromDateToDateWithStatus(SearchRequest searchRequest) {
        return orderRepository.getAllOrdersFromDateToDateWithStatus
                        (searchRequest.getFromDate(),
                                searchRequest.getToDate(),
                                searchRequest.getOrderStatus())
                .stream()
                .map(OrderResponse::convertOrderToOrderResponse)
                .toList();
    }

    public List<OrderResponse> getAll() {
        return orderRepository.findAll().stream()
                .map(OrderResponse::convertOrderToOrderResponse)
                .toList();
    }

    private Order getOrderById(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new NoSuchElementException("There Are No Order With Id = " + orderId)
        );
    }
}
