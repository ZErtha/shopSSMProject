package com.gec.controller;

import com.gec.bean.Category;
import com.gec.bean.Orders;
import com.gec.bean.Product;
import com.gec.bean.User;
import com.gec.service.CategoryService;
import com.gec.service.OrderService;
import com.gec.service.ProductService;
import com.gec.service.UserService;
import com.gec.utils.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/26
 * 佛祖保佑
 * 隆金加滑
 */

/**
 * 管理员所有需要用到的的控制器
 */
@Controller
public class AdminController {

    @Autowired
    HttpSession session;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @RequestMapping("/adminLogin")
    public String adminLogin(String username, String password, Model model){
        User user = userService.getUser(username);
        if (user == null){
            //用户不存在提醒用户去注册
            System.out.println("管理员账号不存在");
            model.addAttribute("error", "账号不存在，请注册");
        }else if(user.getState()!=1){
            System.out.println("该账户不是管理员");
        } else if (!Objects.equals(DigestUtils.md5Hex(password), user.getPassword())) {
            //判断密码是否正确 将传入的密码通过md5加密和数据库进行比对
            //需要更改数据库密码password字段的长度为50
            System.out.println("密码错误，请重新登录");
            model.addAttribute("error", "密码错误，请重新登录");
        }else{
            //如果正确，存储用户信息，登录成功，返回首页
            session.setAttribute("user",user);
            return "admin/index";
        }
        return "admin/adminLogin";
    }

    //分类管理


    @RequestMapping("/adminCategoryList")
    public String adminCategoryList(){
        // 获取session中的类别列表
        List<Category> categoryList = (List<Category>) session.getAttribute("categoryList");
        if (categoryList==null){
            //session中不存在类别列表
            categoryList = categoryService.selectAll();
            session.setAttribute("categoryList",categoryList);
        }
        return "admin/category/list";
    }

    /**
     * 管理员添加类别
     * @param cname
     * @return
     */
    @RequestMapping("/adminAddCategory")
    public String adminAddCategory(String cname){
        if (Objects.equals(cname, "")){
            System.out.println("参数为空，请填写参数");
            //创建失败
            return "admin/category/add";
        }
        //查找类别名是否存在
        System.out.println("查找类别名是否存在 传入的数据是"+cname);
        Category category = categoryService.findCategory(cname);
        System.out.println("获取的类别信息"+category);
        if (category == null){
            //如果不存在，直接创建类别
            //因为数据库中类别id是字符串，所以获取数据库中有多少条数据，新的id再加1
            String cid = categoryService.selectCount();
            Category category1 = new Category();
            category1.setCname(cname);
            String newCid = String.valueOf(Integer.parseInt(cid)+1);
            category1.setCid(newCid);
            int result = categoryService.createCategory(category1);
            if(result>0){
                //创建成功
                //更新下session中存的类别列表信息
                List<Category> categoryList = categoryService.selectAll();
                session.setAttribute("categoryList",categoryList);
                return "admin/category/list";
            }
        }
        //创建失败
        return "admin/category/add";
    }


    //商品管理

    @RequestMapping("/adminProductList")
    public String adminProductList(int currentPage, Model model){
        //管理员

        // 新建分页对象
        Page page = new Page();
        page.setLimit(8);

        //获取每一页的商品数据
        List<Product> productList = productService.findProductPage(page.getOffset(currentPage),page.getLimit(),null,null);

        // 获取商品总数量
        int total = productService.getTotalCount(null,null);
        //更新分页类中的总数量
        page.setTotal(total);

        //将数据传给前端
        model.addAttribute("productList", productList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", page.getTotalPage());

        return "admin/product/list";
    }

    @RequestMapping("/adminAddProduct")
    public String adminAddProduct(Product product, MultipartFile file){
        System.out.println("我进来了adminAddProduct");
        //有图片就上传 没有就不理
        try {
            System.out.println("传入文件是："+file);
            if (file!=null){
                //确定上传的目录
                String path = "D:\\IdeaWork\\shopSSMProject\\web\\products";

                //获得旧的文件的名称
                String originalFilename = file.getOriginalFilename();

                //获取文件的 后缀名 .gif .jpg .png....
                String extension = FilenameUtils.getExtension(originalFilename);

                //重命名文件 (防止相同名称的图片 覆盖)
                String newFilename = UUID.randomUUID().toString().replace("-","")+"."+extension;

                //为了解决一个文件夹下面 太多文件 按照商品分类来建立不同文件夹区分图片
                String filePath = product.getCid();

                //组装 确定好最终的文件名
                String finalName = filePath+"/"+newFilename;

                //创建文件对象
                File destFile = new File(path, finalName);

                //如果文件夹不存在 要创建文件夹
                if (!destFile.getParentFile().exists()){
                    destFile.getParentFile().mkdir();
                }

                //利用io流的方法 将上传的图片的二进制数据文件写到目标文件夹中去
                file.transferTo(destFile);

                //保存最终的图片的位置 存到product中
                product.setPimage("/products/"+finalName);
                product.setPdate(new Date());
                int result = productService.addProduct(product);
                if(result>0){
                    System.out.println("管理员添加商品成功");
                }else{
                    System.out.println("管理员添加商品失败");
                    return "admin/product/add";
                }
                System.out.println("商品信息是："+product);
                System.out.println("图片的访问路径是：/products/"+finalName);

            }else{
                System.out.println("adminAddProduct传入文件是："+ null);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        //调用添加语句 将添加的商品加入到数据库中
        return "redirect:adminProductList?currentPage=1";
    }


    //订单管理


    @RequestMapping("/adminAllOrderList")
    public String adminAllOrderList(int currentPage,Model model){
        //分页
        Page page = new Page();
        page.setLimit(2);
        List<Orders> orderList = orderService.findUserAllOrders();
        model.addAttribute("orderList", orderList);

        page.setTotal(orderList.size());

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", page.getTotalPage());
        return "admin/order/list";
    }
}
