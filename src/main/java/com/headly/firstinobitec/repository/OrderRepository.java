package com.headly.firstinobitec.repository;

import com.headly.firstinobitec.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderRepository {

    @Select("SELECT id FROM \"order\" WHERE id = #{id}")
    Order existByID(Integer id);

    @Select("SELECT * FROM \"order\" WHERE id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "order_status_id", property = "orderStatusId"),
            @Result(column = "customer_name", property = "customerName"),
            @Result(column = "customer_phone", property = "customerPhone"),
            @Result(column = "customer_comment", property = "customerComment"),
            @Result(column = "id", property = "orderItemList",
                    javaType = List.class, many = @Many(
                    select = "com.headly.firstinobitec." +
                            "repository.OrderItemRepository.getOrderItemListById"
            ))
    })
    Order getOrderById(Integer id);

    @Select("SELECT max(id) FROM \"order\"")
    Integer getMaxOrderId();

    @Delete("WITH t2_deleted as (DELETE FROM order_item WHERE order_id = #{id})" +
            "DELETE FROM \"order\" WHERE id = #{id};")
    void deleteOrderById(Integer id);

    @Insert("INSERT INTO \"order\" (id, order_status_id, customer_name, " +
            "customer_phone, customer_comment)" +
            "VALUES(NEXTVAL('ORDER_SEQ'), #{orderStatusId}, #{customerName}, " +
            "#{customerPhone}, #{customerComment})" +
            "RETURNING id;")
    void insertOrder(Order order);

    @Update("UPDATE \"order\" " +
            "SET order_status_id = #{orderStatusId}, " +
            "customer_name = #{customerName}," +
            "customer_phone = #{customerPhone}," +
            "customer_comment = #{customerComment}" +
            "WHERE id=#{id}")
    void updateOrder(@Param("id") Integer id,
                     @Param("orderStatusId") Integer orderStatusId,
                     @Param("customerName") String customerName,
                     @Param("customerPhone") String customerPhone,
                     @Param("customerComment") String customerComment);
}
