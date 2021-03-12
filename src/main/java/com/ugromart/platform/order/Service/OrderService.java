package com.ugromart.platform.order.Service;

import com.ugromart.platform.order.Order;
import com.ugromart.platform.order.OrderProcessor;
import com.ugromart.platform.order.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;



@Service
public class OrderService {
    private static final Logger log= LoggerFactory.getLogger(OrderService.class);
    private OrderProcessor orderProcessor;

    @Autowired
    public OrderService(OrderProcessor orderProcessor){
        this.orderProcessor=orderProcessor;
    }

    //@StreamListener(OrderProcessor.ORDERS_IN)
    public void checkAndPublishOrder(Order order){
        log.info("Order Created for customerId: {} worth {}",order.getUserId(),order.getTotalOrder().getAmount());
        order.setStatus(OrderStatus.PLACED.name());
        orderProcessor.sourceOfOrders().send(message(order));
    }

    private static final <T> Message<T> message(T val){ return MessageBuilder.withPayload(val).build();
    }

}
