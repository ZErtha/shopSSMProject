package com.gec.controller;

import com.gec.bean.Category;
import com.gec.bean.Product;
import com.gec.service.CategoryService;
import com.gec.service.ProductService;
import com.gec.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/25
 * 佛祖保佑
 * 隆金加滑
 */
@Controller
public class ProductController {

    @Autowired
    HttpSession session;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    /**
     * 首页
     * 获取导航栏的类别列表
     * 获取最热商品，最新商品
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model) {
        //使用session保存导航栏需要的类别列表，可以和其他页面共享
        //设置了之后就不用在其他控制器传值

        // 获取session中的类别列表
        List<Category> categoryList = (List<Category>) session.getAttribute("categoryList");

        //获取最热商品，最新商品
        List<Product> hotProductList = productService.findHotProduct();
        List<Product> newProductList = productService.findNewProduct();

        if (categoryList==null){
            //session中不存在类别列表
            categoryList = categoryService.selectAll();
            session.setAttribute("categoryList",categoryList);
        }
        model.addAttribute("hotProductList", hotProductList);
        model.addAttribute("newProductList", newProductList);
        return "index";
    }

    /**
     * 根据每个类别id查询对应类别的所有商品(导航栏中类别列表)
     * @param currentPage
     * @param cid
     * @param model
     * @return
     */
    @RequestMapping(value = "/productList")
    public String ProductListServlet(int currentPage, String cid, Model model) {

        // 新建分页对象
        Page page = new Page();
        page.setLimit(12);

        //获取每一页的商品数据
        List<Product> productList = productService.findProductPage(page.getOffset(currentPage),page.getLimit(),cid,null);

        // 获取商品总数量
        int total = productService.getTotalCount(cid,null);
        //更新分页类中的总数量
        page.setTotal(total);

        // 获取session中的类别列表
        List<Category> categoryList = (List<Category>) session.getAttribute("categoryList");

        //让前端可以显示类别标题
        String categoryName="全部商品";
        for(Category category: categoryList){
            if(Objects.equals(category.getCid(), cid)){
                categoryName=category.getCname();
                break;
            }
        }

        //将数据传给前端
        model.addAttribute("productList", productList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", page.getTotalPage());
        model.addAttribute("cid", cid);
        model.addAttribute("categoryName", categoryName);
        return "product_list";
    }

    /**
     * 搜索商品
     * @param currentPage
     * @param search
     * @param model
     * @return
     */
    @RequestMapping(value = "/findProduct")
    public String SearchProductServlet(int currentPage, String search, Model model) {
        // 新建分页对象
        Page page = new Page();
        page.setLimit(12);

        //获取每一页的商品数据
        List<Product> productList = productService.findProductPage(page.getOffset(currentPage),page.getLimit(),null,search);

        // 获取商品总数量
        int total = productService.getTotalCount(null,search);
        //更新分页类中的总数量
        page.setTotal(total);

        //将数据传给前端
        model.addAttribute("productList", productList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", page.getTotalPage());
        model.addAttribute("searchName", search);
        return "search";
    }

    /**
     * 商品详情页
     * @param pid
     * @param model
     * @return
     */
    @RequestMapping(value = "/productInfo")
    public String ProductInfoServlet(int pid, Model model) {
        Product product = productService.findProductById(pid);
        model.addAttribute("product", product);
        return "product_info";
    }

}