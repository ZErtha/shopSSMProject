package com.gec.service;

import com.gec.bean.Product;

import java.util.List;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/24
 * 佛祖保佑
 * 隆金加滑
 */
public interface ProductService {
    List<Product> findHotProduct();
    List<Product> findNewProduct();
    List<Product> findProductPage(Integer offset, Integer limit, String cid,String pname);
    int getTotalCount(String cid,String pname);
    Product findProductById(int pid);

    int addProduct(Product product);
}
