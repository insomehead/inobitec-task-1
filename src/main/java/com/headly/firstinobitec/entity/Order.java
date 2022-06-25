package com.headly.firstinobitec.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter

public class Order {


    private Long id;


    private Integer orderStatusId;


    private String customerName;


    private String customerPhone;


    private String customerComment;

    private List<OrderItem> orderItemList;
}
