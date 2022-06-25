package com.headly.firstinobitec.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "order_item")
public class OrderItem {

    @Column(name = "id")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "item_name")
    private String itemName;
}
