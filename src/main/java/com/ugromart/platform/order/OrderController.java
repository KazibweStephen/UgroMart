package com.ugromart.platform.order;

import com.ugromart.platform.configuration.UserNotFoundException;
import com.ugromart.platform.order.Service.OrderService;
import com.ugromart.platform.order.exceptions.OrderValidationException;
import com.ugromart.platform.order.exceptions.ProductNotFoundException;
import com.ugromart.platform.order.models.Order;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@Tag(name="Orders")
public class OrderController {
    private static final Logger log= LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order){
         orderService.validateOrder(order);
         orderService.checkAndPublishOrder(order);
         order.setStatus(OrderStatus.PLACED.name());
         return ResponseEntity.ok(order);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable("customerId") long customerId){
        List<Order> response= orderService.getCustomerOrders(customerId);
        return ResponseEntity.ok().body(response);
    }
}
