package com.ugromart.platform.product.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Serializable {
    private int id;
    private String name;
    private String description;
    private BigDecimal sellingPrice;

    public Product() {
    }

    public Product(int id, String name, String description, BigDecimal sellingPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sellingPrice = sellingPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                name.equals(product.name) &&
                Objects.equals(description, product.description) &&
                sellingPrice.equals(product.sellingPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, sellingPrice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
