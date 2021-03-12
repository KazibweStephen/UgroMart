package com.ugromart.platform.order;

import com.ugromart.platform.order.Service.OrderService;
import com.ugromart.platform.user.models.User;
import com.ugromart.platform.user.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@Tag(name="Orders")
public class OrderController {
    private static final Logger log= LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order){
        User user =userService.findUserById(order.getUserId());
        if(user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if(order.getTotalOrder().getAmount().doubleValue()<=0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        orderService.checkAndPublishOrder(order);
        order.setStatus(OrderStatus.PLACED.name());
        return ResponseEntity.ok(order);
    }
}
