package com.ugromart.platform.order.Service;

import com.ugromart.platform.configuration.UserNotFoundException;
import com.ugromart.platform.order.models.Order;
import com.ugromart.platform.order.OrderProcessor;
import com.ugromart.platform.order.OrderStatus;
import com.ugromart.platform.order.exceptions.OrderValidationException;
import com.ugromart.platform.order.exceptions.ProductNotFoundException;
import com.ugromart.platform.order.models.OrderItem;
import com.ugromart.platform.product.models.Product;
import com.ugromart.platform.product.services.ProductsRestService;
import com.ugromart.platform.user.models.User;
import com.ugromart.platform.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {
    private static final Logger log= LoggerFactory.getLogger(OrderService.class);
    private OrderProcessor orderProcessor;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductsRestService productsRestService;

    @Autowired
    private OrdersRestService ordersRestService;
    @Autowired
    public OrderService(OrderProcessor orderProcessor){
        this.orderProcessor=orderProcessor;
    }

    public void validateOrder(Order order){
        User user =userService.findUserById(order.getUserId());
        if(user==null){
                throw new UserNotFoundException(String.format("User with id {} not found",order.getUserId()));
        }
        if(order.getTotalOrder().getAmount().doubleValue()<=0){
            throw new OrderValidationException("Order total must be greater than 0.0SH");
        }
        StringBuilder sb= new StringBuilder();
        for(OrderItem item:order.getOrderItems()){
            try{
                Product existingProduct=productsRestService.getProductById(item.getProductId());
            }catch (Exception ex){
                sb.append(String.format("Product with Id %d not found, \n",item.getProductId()));
            }
        }
        if(sb.length()>0){
            log.error(sb.toString());
            throw  new ProductNotFoundException(sb.toString());
        }

    }
    //@StreamListener(OrderProcessor.ORDERS_IN)
    public void checkAndPublishOrder(Order order){
        log.info("Order Created for customerId: {} worth {}",order.getUserId(),order.getTotalOrder().getAmount());
        order.setStatus(OrderStatus.PLACED.name());
        orderProcessor.sourceOfOrders().send(message(order));
    }

    private static final <T> Message<T> message(T val){ return MessageBuilder.withPayload(val).build();
    }

    public List<Order> getCustomerOrders(long customerId) {
        return ordersRestService.getOrdersByCustomerId(customerId);
    }
}
