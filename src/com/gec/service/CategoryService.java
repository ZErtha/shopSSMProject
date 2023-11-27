package com.gec.service;

import com.gec.bean.Category;

import java.util.List;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/25
 * 佛祖保佑
 * 隆金加滑
 */
public interface CategoryService {
    List<Category> selectAll();

    Category findCategory(String categoryName);

    int createCategory(Category category);

    String selectCount();
}
