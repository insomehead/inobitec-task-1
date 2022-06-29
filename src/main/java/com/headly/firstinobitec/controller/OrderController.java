package com.headly.firstinobitec.controller;

import com.headly.firstinobitec.model.Order;
import com.headly.firstinobitec.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id) {
        if (!orderService.existById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (!orderService.existById(id)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        orderService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<Void> insertOrder(@RequestBody Order order){
        if (orderService.existById(order.getId())){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        orderService.insertOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable Integer id, @RequestBody Order order){
        if (!orderService.existById(id)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        orderService.updateOrder(id, order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
