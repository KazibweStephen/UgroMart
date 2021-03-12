package com.ugromart.platform.product;

import com.ugromart.platform.order.Money;
import lombok.Data;

@Data
public class Product {
    private int productId;
    private String productName;
    private Money price;
}
