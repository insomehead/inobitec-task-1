package com.headly.firstinobitec.controller;

import com.headly.firstinobitec.entity.Order;
import com.headly.firstinobitec.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/order/{id}")
    public Order findById(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }

}
