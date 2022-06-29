package com.headly.firstinobitec.service;

import com.headly.firstinobitec.model.Order;
import com.headly.firstinobitec.model.OrderItem;
import com.headly.firstinobitec.repository.OrderItemRepository;
import com.headly.firstinobitec.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Order findById(Integer id) {
        return orderRepository.getOrderById(id);
    }

    public boolean existById(Integer id) {
        return orderRepository.existByID(id) != null;
    }

    public void deleteById(Integer id) {
        orderRepository.deleteOrderById(id);
    }

    public void insertOrder(Order order) {
        orderRepository.insertOrder(order);
        for (int i = 0; i < order.getOrderItemList().size(); i++) {
            orderItemRepository.insertListOrder(order.getOrderItemList().get(i),
                    orderRepository.getMaxOrderId());
        }
    }

    public void updateOrder(Integer id, Order order) {
        List<OrderItem> oldOrderItemList = orderRepository.getOrderById(id).getOrderItemList();
        List<OrderItem> orderItemList = order.getOrderItemList();
        orderRepository.updateOrder(id, order.getOrderStatusId(),
                order.getCustomerName(),
                order.getCustomerPhone(),
                order.getCustomerComment());
        if (orderItemList == null || orderItemList.isEmpty()) {
            orderItemRepository.deleteOrderItemByOrderId(id);
            return;
        }
        if (orderItemList.size() == oldOrderItemList.size()) {
            for (int i = 0; i < orderItemList.size(); i++) {
                orderItemRepository.updateOrderItem(
                        oldOrderItemList.get(i).getId(),
                        id,
                        orderItemList.get(i).getItemName()
                );
            }
        }
        if (orderItemList.size() < oldOrderItemList.size()) {
            for (int i = 0; i < orderItemList.size(); i++) {
                orderItemRepository.updateOrderItem(
                        oldOrderItemList.get(i).getId(),
                        id,
                        orderItemList.get(i).getItemName()
                );
            }
            for (int i = orderItemList.size(); i < oldOrderItemList.size(); i++) {
                orderItemRepository.deleteOrderItemById(oldOrderItemList.get(i).getId());
            }
        }
        if (orderItemList.size() > oldOrderItemList.size()) {
            for (int i = 0; i < oldOrderItemList.size(); i++) {
                orderItemRepository.updateOrderItem(
                        oldOrderItemList.get(i).getId(),
                        id,
                        orderItemList.get(i).getItemName()
                );
            }
            for (int i = oldOrderItemList.size(); i < orderItemList.size(); i++) {
                orderItemRepository.insertListOrder(orderItemList.get(i), orderRepository.getMaxOrderId());
            }
        }
    }
}