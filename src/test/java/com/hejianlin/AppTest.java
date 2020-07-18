package com.hejianlin;

import static org.junit.Assert.assertTrue;

import com.hejianlin.model.Order;
import com.hejianlin.util.GenerateSqlUtil;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testSql() throws Exception {
        Order order = new Order();
        order.setShopId(101L);
        order.setOrderName("维他柠檬茶");
        String querySql = GenerateSqlUtil.query(order);
        System.out.println("查询sql:"+querySql);
    }
}
