package com.embrace.springsecurity.controller;

import com.embrace.springsecurity.dto.Product;
import com.embrace.springsecurity.entity.UserInfo;
import com.embrace.springsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAlltheProducts(){
        return  service.getProducts();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public  Product getProductByID(@PathVariable int id){
        return  service.getProduct(id);

    }
}


