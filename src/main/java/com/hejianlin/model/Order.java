package com.hejianlin.model;

import com.hejianlin.annoation.Column;
import com.hejianlin.annoation.SqlState;
import lombok.Data;

@SqlState("t_order")
@Data
public class Order {

    /**
     * id
     */
    @Column //没有指定字段名，则默认和属性名相同
    private Long id;
    /**
     * 订单号
     */
    @Column("order_no")
    private Long orderNo;
    /**
     * 订单名称
     */
    @Column("order_name")
    private String orderName;
    /**
     * 商店id
     */
    @Column("shop_id")
    private Long shopId;
    /**
     * 用户id
     */
    @Column("user_id")
    private Long userId;

}
