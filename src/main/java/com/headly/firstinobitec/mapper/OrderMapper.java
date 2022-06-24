package com.headly.firstinobitec.mapper;

import com.headly.firstinobitec.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface OrderMapper {

    @Select("SELECT * " +
            "FROM \"order\" " +
            "WHERE id = #{id}")
    Order findById(Long id);

    //сложна. нада думать
    @Select("SELECT * " +
            "FROM \"order\" " +
            "LEFT JOIN order_item on \"order\".id = order_item.order_id")
    @Results(value = {
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "item_name", property="itemName")
    })
    List<Order> findAll();
}
