package com.headly.firstinobitec.repository;

import com.headly.firstinobitec.model.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderItemRepository {

    @Insert("INSERT INTO order_item (id, order_id, item_name)" +
            "VALUES(NEXTVAL('ORDER_ITEM_SEQ'), #{order_id}, #{orderItem.itemName})")
    void insertListOrder(@Param("orderItem") OrderItem orderItem, @Param("order_id") Integer order_id);

    @Select("SELECT * FROM order_item WHERE order_id = #{orderId}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "item_name", property = "itemName")
    })
    List<OrderItem> getOrderItemListById(Integer orderId);

    @Update("UPDATE order_item " +
            "SET " +
            "item_name=#{itemName}" +
            "WHERE id=#{id} AND order_id=#{order_id}")
    void updateOrderItem(@Param("id") Integer id,
                         @Param("order_id") Integer order_id,
                         @Param("itemName") String itemName);

    @Delete("DELETE FROM order_item WHERE order_id = #{id}")
    void deleteOrderItemByOrderId(Integer id);

    @Delete("DELETE FROM order_item WHERE id = #{id}")
    void deleteOrderItemById(Integer id);
}
