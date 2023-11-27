package com.gec.controller;


import com.gec.bean.Cart;
import com.gec.bean.CartItem;
import com.gec.bean.Product;
import com.gec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/25
 * 佛祖保佑
 * 隆金加滑
 */
@Controller
public class CartController {

    @Autowired
    ProductService productService;

    @Autowired
    HttpSession session;


    /**
     *添加购物车
     * @param pid
     * @param buyNum
     * @return
     */
    @RequestMapping("/addCart")     //表示接受请求的地址
    public String addCart(int pid,int buyNum){
        //先判断 用户是否已经存在购物车（购物车是在session中)
        Cart cart = (Cart) session.getAttribute("cart");

        //购物车不存在
        if (cart==null){
            //创建购物车
            cart = new Cart();
        }

        //如果已经存在购物车，直接往购物车中添加商品
        //往购物车添加商品
        //考虑 购物车是不是已经有这个商品
        //1、获取购物车中的每个商品 进行遍历查看是否已经存在这个商品
        Map<String, CartItem> cartItemMap = cart.getCartItems();
        //购物车明细 cartItems存放数据的格式是 购物车商品编号+购物车中的商品明细对象 的键值对来存放的
        //简单点只用判断这个商品的编号是否存在map容器中就好了
        Set<String> cartPidS = cartItemMap.keySet();

        //定义一个参数用来记录 购物车中的商品是否存在重复
        int count=0;

        //将购物车中每个商品明细的商品编号拿出来 比较 看着我们要添加的这个商品是否存在
        for(String cartPid:cartPidS){

            //pid+""将要提交过来的商品编号 换成字符串的类型
            if (cartPid.equals(pid+"")){
                //如果购物车中 已经有一个同款商品 那么这个商品的数量就要加1 修改数量就好了
                CartItem cartItem = cartItemMap.get(cartPid);
                //购物车中商品原来的数量
                int num = cartItem.getBuyNum();
                //修改购物车中商品明细的购买数量
                //原来的数量加上 现在新加过来的数量
                cartItem.setBuyNum(num+buyNum);
                //将修改以后的购物车明细返回购物车对象去
                cartItemMap.put(cartPid+"",cartItem);
                //如果 购物车中存在 这个商品 count+1表示 有一个重复商品
                count++;
            }
        }

        // 购物车没有这个商品 就直接添加
        if (count==0){
            //要获取商品信息 查询商品信息 根据用户提交的商品编号获取商品信息
            Product product = productService.findProductById(pid);
            //创建一个购物车商品明细对象
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setBuyNum(buyNum);
            //将这个购物车明细加入购物车中
            cartItemMap.put(pid+"",cartItem);
        }

        //将购物车存放到session 中 方便购物车数据在jsp页面存放还有就是实现不同页面可以共享购物车的数据
        session.setAttribute("cart",cart);

        //将页面切换到 购物车页面
        return "cart";
    }


    /**
     * 清空购物车
     * @return
     */
    @RequestMapping(value = "/cleanCart")
    public String CleanCartServlet() {
        session.removeAttribute("cart");
        return "cart";
    }

    /**
     * 购物车修改数量
     * @param pid
     * @param buyNum
     * @return
     */
    @RequestMapping("/modifyCartBuyNum")
    public String UpdateBuyNumServlet(String pid,int buyNum){
        // 获取session中购物车的数据
        Cart cart = (Cart) session.getAttribute("cart");

        // 拿到购物车中所有的订单明细
        Map<String, CartItem> cartItemMap = cart.getCartItems();
        //购物车明细 cartItems存放数据的格式是 购物车商品编号+购物车中的商品明细对象 的键值对来存放的
        //简单点只用判断这个商品的编号是否存在map容器中就好了
        Set<String> cartPidS = cartItemMap.keySet();

        //将购物车中每个商品明细的商品编号拿出来 比较 看着我们要添加的这个商品是否存在
        for(String cartPid:cartPidS){
            //pid+""将要提交过来的商品编号 换成字符串的类型
            if (cartPid.equals(pid+"")){
                CartItem cartItem = cartItemMap.get(cartPid);
                //修改购物车中商品明细的购买数量
                cartItem.setBuyNum(buyNum);
                //将修改以后的购物车明细返回购物车对象去
                cartItemMap.put(cartPid+"",cartItem);
                break;
            }
        }
        return "cart";
    }


    /**
     * 删除购物车的商品
     * @param pid
     * @return
     */
    @RequestMapping("/deleteCartByProduct")
    public String DeleteCartItemServlet(String pid){
        // 获取session中购物车的数据
        Cart cart = (Cart) session.getAttribute("cart");

        // 拿到购物车中所有的订单明细
        Map<String, CartItem> cartItems = cart.getCartItems();
        Collection<CartItem> values = cartItems.values();
        // 遍历购物车明细
        int deleteFlag = 0;
        for (CartItem item:values){
            Iterator<CartItem> it_b= values.iterator();
            while(it_b.hasNext()){
                item=it_b.next();
                if (Objects.equals(item.getProduct().getPid(), pid)){
                    it_b.remove();
                    deleteFlag = 1;
                    break;
                }
            }
            if (deleteFlag==1){
                break;
            }
        }
        return "cart";
    }
}
