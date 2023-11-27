package com.gec.service;

import com.gec.bean.Orders;

import java.util.List;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/25
 * 佛祖保佑
 * 隆金加滑
 */
public interface OrderService {
    Orders createOrder(Orders orders);

    List<Orders> findUserAllOrders();

    List<Orders> findUserOrdersByUid(String uid);

    Orders getOrdersByOid(String oid);

    int updateOrders(Orders record);
}
