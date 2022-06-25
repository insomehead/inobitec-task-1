package com.headly.firstinobitec.service;

import com.headly.firstinobitec.entity.Order;
import com.headly.firstinobitec.entity.OrderItem;
import com.headly.firstinobitec.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderMapper orderMapper){
        this.orderMapper = orderMapper;
    }

    public Order findById(Long id){
        return orderMapper.orderList(id);
    }

    public boolean existById(Long id){
        return orderMapper.existByID(id) != null;
    }

    public List<OrderItem> list(Long orderId){
        return orderMapper.orderItemList(orderId);
    }
}
