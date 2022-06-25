package com.headly.firstinobitec.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter

public class OrderItem {


    private Long id;


    private Long orderId;


    private String itemName;
}
