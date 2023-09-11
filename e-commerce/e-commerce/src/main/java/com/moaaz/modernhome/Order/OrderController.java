package com.moaaz.modernhome.Order;

import com.moaaz.modernhome.User.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/moaaz/api/modernhome/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/addOrder/{userId}")
    public ResponseEntity<?> addOrder(@PathVariable long userId) {
        orderService.addOrder(userId);
        return new ResponseEntity<>("Order Added Successfully", HttpStatus.CREATED);
    }

    @PostMapping("/accept/{orderId}")
    public ResponseEntity<?> acceptOrder(@PathVariable long orderId) {
        orderService.acceptOrder(orderId);
        return new ResponseEntity<>("Accepted Successfully", HttpStatus.ACCEPTED);
    }

    @PostMapping("/complete/{orderId}")
    public ResponseEntity<?> completeOrder(@PathVariable long orderId) {
        orderService.completeOrder(orderId);
        return new ResponseEntity<>("Completed Successfully", HttpStatus.ACCEPTED);

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getAllBySearch")
    public ResponseEntity<?> getAllBySearch(@RequestBody SearchRequest searchRequest) {
        return new ResponseEntity<>(orderService.getAllOrdersFromDateToDateWithStatus(searchRequest), HttpStatus.OK);
    }

}
