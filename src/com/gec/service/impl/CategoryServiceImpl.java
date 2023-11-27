package com.gec.service.impl;

import com.gec.bean.Category;
import com.gec.dao.CategoryMapper;
import com.gec.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public Category findCategory(String categoryName) {
        return categoryMapper.selectCategoryName(categoryName);
    }

    @Override
    public int createCategory(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public String selectCount() {
        return categoryMapper.selectCount();
    }
}
