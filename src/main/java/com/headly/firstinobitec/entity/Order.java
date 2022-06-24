package com.headly.firstinobitec.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_status_id")
    private Integer orderStatusId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_comment")
    private String customerComment;
}
