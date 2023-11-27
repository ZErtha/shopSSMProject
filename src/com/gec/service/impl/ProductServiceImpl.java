package com.gec.service.impl;

import com.gec.bean.Product;
import com.gec.dao.ProductMapper;
import com.gec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/24
 * 佛祖保佑
 * 隆金加滑
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    /**
     * 获取最热商品
     * @return
     */
    @Override
    public List<Product> findHotProduct() {
        return productMapper.findHotProduct();
    }

    /**
     * 获取最新商品
     * @return
     */
    @Override
    public List<Product> findNewProduct() {
        return productMapper.findNewProduct();
    }

    /**
     * 商品分页查询
     * @return
     */
    @Override
    public List<Product> findProductPage(Integer offset, Integer limit, String cid, String pname) {
        return productMapper.findProductPage(offset, limit,cid,pname);
    }

    /**
     * 查询总共商品数量
     * 可以通过商品类别查，也可以通过商品名查
     * @return
     */
    @Override
    public int getTotalCount(String cid, String pname) {
        return productMapper.getTotalCount(cid,pname);
    }


    @Override
    public Product findProductById(int pid) {
        return productMapper.selectByPrimaryKey((long) pid);
    }

    /**
     * 添加商品 因为商品编号是数字 而数据库存储类型是字符串 所以要统计一下商品的数量 产生一个新的序列
     * 作为新的商品编号
     * @param product
     * @return
     */
    @Override
    public int addProduct(Product product) {
        //先获取一共有多少个商品 利用商品数量加1 作为新的商品主键
        String pid = String.valueOf(productMapper.getTotalCount(null,null)+1);
        product.setPid(pid);
        return productMapper.insert(product);
    }
}
