package com.gec.dao;

import com.gec.bean.Category;

import java.util.List;

/**
* @author 泽申（Ertha)
* @description 针对表【category】的数据库操作Mapper
* @createDate 2023-04-24 21:57:45
* @Entity com.gec.bean.Category
*/
public interface CategoryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectAll();

    Category selectCategoryName(String categoryName);

    String selectCount();

}
