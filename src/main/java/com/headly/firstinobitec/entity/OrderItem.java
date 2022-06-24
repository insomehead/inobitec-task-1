package com.headly.firstinobitec.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {

    @Column(name = "id")
    private Long id;

    @Column(name = "order_id")
    private Long order_id;

    @Column(name = "item_name")
    private String item_name;
}
