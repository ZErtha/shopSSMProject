package com.gec.dao;

import com.gec.bean.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 泽申（Ertha)
* @description 针对表【product】的数据库操作Mapper
* @createDate 2023-04-24 21:57:45
* @Entity com.gec.bean.Product
*/
public interface ProductMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> findHotProduct();
    List<Product> findNewProduct();
    List<Product> findProductPage(@Param("offset") Integer offset,
                                  @Param("limit") Integer limit,
                                  @Param("cid") String cid,
                                  @Param("pname") String pname);
    int getTotalCount(@Param("cid") String cid,@Param("pname") String pname);
}
