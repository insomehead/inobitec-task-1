package com.headly.firstinobitec.mapper;

import com.headly.firstinobitec.entity.Order;
import com.headly.firstinobitec.entity.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface OrderMapper {

    @Select("SELECT id FROM \"order\" WHERE id = #{id}")
    Order existByID(Long id);

    @Select("SELECT * FROM \"order\" WHERE id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "order_status_id", property = "orderStatusId"),
            @Result(column = "customer_name", property = "customerName"),
            @Result(column = "customer_phone", property = "customerPhone"),
            @Result(column = "customer_comment", property = "customerComment"),
            @Result(column = "id", property = "orderItemList",
                    javaType = List.class, many = @Many(
                    select = "getOrderListById"
            ))
    })
    Order getOrderById(Long id);

    @Select("SELECT * FROM order_item WHERE order_id = #{orderId}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "item_name", property = "itemName")
    })
    List<OrderItem> getOrderListById(Long orderId);

    @Delete("WITH t2_deleted as (DELETE FROM order_item WHERE order_id = #{id})" +
            "DELETE FROM \"order\" WHERE id = #{id};")
    void deleteOrderById(Long id);
}
