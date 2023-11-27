package com.gec.dao;

import com.gec.bean.Orders;

import java.util.List;

/**
* @author 泽申（Ertha)
* @description 针对表【orders】的数据库操作Mapper
* @createDate 2023-04-24 21:57:45
* @Entity com.gec.bean.Orders
*/
public interface OrdersMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> findUserOrdersByUid(String uid);

    Orders findOrdersByOid(String oid);

    List<Orders> findUserAllOrders();

}
