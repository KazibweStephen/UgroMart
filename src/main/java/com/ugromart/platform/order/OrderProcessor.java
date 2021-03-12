package com.ugromart.platform.order;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface OrderProcessor {
    String ORDERS_IN="orders_created";

    @Output(ORDERS_IN)
    MessageChannel sourceOfOrders();
}
