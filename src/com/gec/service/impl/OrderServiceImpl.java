package com.gec.service.impl;

import com.gec.bean.Orderitem;
import com.gec.bean.Orders;
import com.gec.dao.OrderitemMapper;
import com.gec.dao.OrdersMapper;
import com.gec.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/25
 * 佛祖保佑
 * 隆金加滑
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    OrderitemMapper orderitemMapper;

    /**
     * 创建订单
     * @param orders
     * @return
     */
    @Override
    public Orders createOrder(Orders orders) {
        //先添加订单，再添加订单明细
        int result = ordersMapper.insert(orders);
        if (result>0){
            //一个订单中 可能有多个订单明细
            List<Orderitem>  orderitemList = orders.getOrderItems();
            for(Orderitem orderitem:orderitemList){
                orderitemMapper.insert(orderitem);
            }
        }
        return orders;
    }

    @Override
    public List<Orders> findUserAllOrders() {
        return ordersMapper.findUserAllOrders();
    }

    /**
     * 根据用户uid查询
     * @param uid
     * @return
     */
    @Override
    public List<Orders> findUserOrdersByUid(String uid) {
        return ordersMapper.findUserOrdersByUid(uid);
    }

    @Override
    public Orders getOrdersByOid(String oid) {
        return ordersMapper.findOrdersByOid(oid);
    }

    @Override
    public int updateOrders(Orders record) {
        return ordersMapper.updateByPrimaryKey(record);
    }
}
