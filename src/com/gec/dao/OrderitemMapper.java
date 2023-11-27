package com.gec.dao;

import com.gec.bean.Orderitem;

/**
* @author 泽申（Ertha)
* @description 针对表【orderitem】的数据库操作Mapper
* @createDate 2023-04-24 21:57:45
* @Entity com.gec.bean.Orderitem
*/
public interface OrderitemMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Orderitem record);

    int insertSelective(Orderitem record);

    Orderitem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Orderitem record);

    int updateByPrimaryKey(Orderitem record);

}
