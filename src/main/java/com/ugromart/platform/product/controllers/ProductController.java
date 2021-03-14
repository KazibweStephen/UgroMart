package com.ugromart.platform.product.controllers;

import com.ugromart.platform.product.models.Product;
import com.ugromart.platform.product.models.Vendor;
import com.ugromart.platform.product.services.ProductsRestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Products")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductsRestService productsRestService;

    @GetMapping("/")
    public List<Product> getProducts(){
        return productsRestService.getProduts();
    }

    @GetMapping("/vendors")
    public  List<Vendor> getVendors(){
            return productsRestService.getVendors();
    }

}
