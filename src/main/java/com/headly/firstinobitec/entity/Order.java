package com.headly.firstinobitec.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Table(name = "order")
public class Order {

    @Override
    public int hashCode() {
        return Objects.hash(id, orderStatusId, customerName, customerPhone, customerComment, orderItemList);
    }

    @Column(name = "id")
    private Long id;

    @Column(name = "order_status_id")
    private Integer orderStatusId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_comment")
    private String customerComment;

    private List<OrderItem> orderItemList;
}
