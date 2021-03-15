package com.ugromart.platform.order.models;

import com.ugromart.platform.order.models.Money;
import com.ugromart.platform.product.models.Product;

import java.util.List;


public class Order {
    private long orderId;
    private long userId;
    private String orderDate;
    private Money totalOrder;
    private  String status;
    private List<OrderItem> orderItems;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Money getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Money totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
