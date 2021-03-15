package com.ugromart.platform.order.Service;

import com.ugromart.platform.order.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrdersRestService {
    @Autowired
    OrdersRestConnector ordersRestConnector;

    public List<Order> getOrders(){
        Order[] orders=ordersRestConnector.get(Order[].class,"/order");
        return Arrays.asList(orders);
    }
    public List<Order> getOrdersByCustomerId(long customerId){
        Order[] order=ordersRestConnector.get(Order[].class,"/order/"+customerId);
        return Arrays.asList(order);
    }


}
