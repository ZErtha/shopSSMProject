package com.gec.controller;

import com.gec.bean.*;
import com.gec.service.CategoryService;
import com.gec.service.OrderService;
import com.gec.service.ProductService;
import com.gec.utils.OrderStateEnum;
import com.gec.utils.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/25
 * 佛祖保佑
 * 隆金加滑
 */
@Controller
public class OrderController {

    @Autowired
    HttpSession session;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    CategoryService categoryService;

    private void getItemFromCart(Cart cart, Orders orders) {

        Map<String, CartItem> cartItems = cart.getCartItems();
        Collection<CartItem> values = cartItems.values();

        orders.setTotal(cart.getTotal());

        for (CartItem item:values){
            Orderitem orderitem = new Orderitem();
            orderitem.setCount(item.getBuyNum());
            orderitem.setItemid(UUID.randomUUID().toString());
            orderitem.setOid(orders.getOid());
            orderitem.setProduct(item.getProduct());
            orderitem.setPid(item.getProduct().getPid());
            orderitem.setSubtotal(item.getSubTotal());
            orders.getOrderItems().add(orderitem);
        }
    }

    /**
     * 点击结算
     * 创建订单,提交订单
     * 用户提交订单发送的请求
     * @return
     */
    @RequestMapping(value = "/createOrder")
    public String createOrderServlet(HttpServletRequest request,Model model) {
        //获取请求资源
        String requestURI = request.getRequestURI();
        System.out.println("获取请求资源"+requestURI);

        StringBuffer requestURL = request.getRequestURL();
        System.out.println("获取请求资源"+requestURL);


        //提交订单需要知道是哪个用户提交的 确定一下用户是否登录 有登录才能结算，没有登录就去登录
        //使用session这个会话空间 判断里面有没有用户信息 如果有 用户有登录 没有就是用户没有登录
        User user = (User) session.getAttribute("user");
        if (user == null){
            //判断里面没有用户信息，没有就是用户没有登录
            //重定向到 登录页面 让用户先进行登录
            return "redirect:login.jsp";
        }
        Cart cart = (Cart) session.getAttribute("cart");
        Orders orders = new Orders();
        orders.setOid(UUID.randomUUID().toString());

        getItemFromCart(cart, orders);

        //将状态改为未支付
        orders.setState(0);
        orders.setAddress(user.getAddress());
        orders.setName(user.getName());
        orders.setTelephone(user.getTelephone());
        model.addAttribute("order",orders);
        return "order_info";
    }

    /**
     * 点击结算后，确认订单
     * 生成订单
     * @param model
     * @param orders
     * @return
     */
    @RequestMapping(value = "/submitOrder")
    public String submitOrder(Model model, Orders orders){
        // 先判断用户是否有登录
        User user = (User) session.getAttribute("user");
        if (user == null){
            //未登录，去登录
            return "redirect:login.jsp";
        }
        //再判断购物车中是否有商品
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
            //购物车对象为空 就让用户去添加商品到购物车
            return "redirect:cart.jsp";
        }
        //创建订单 调用创建订单的业务逻辑代码
        //准备好订单对象数据 和订单明细的数据
        //准备订单的数据
        //将订单绑定给当前uid

        orders.setUid(user.getUid());
        orders.setState(0);
        orders.setOrdertime(new Date());

        getItemFromCart(cart, orders);

        orderService.createOrder(orders);
        model.addAttribute("order",orders);

        //将购物车数据清除
        session.removeAttribute("cart");

        return "account";
    }

    /**
     * 我的订单
     * 查看订单明细
     * @param currentPage
     * @param cid
     * @param model
     * @return
     */
    @RequestMapping(value = "/orderList")
    public String OrderListServlet(int currentPage, String cid, Model model) {
        //获取用户信息
        User user = (User) session.getAttribute("user");
        if (user == null){
            //未登录，去登录
            return "redirect:login.jsp";
        }
        //如果用户不为空，就查看该用户是否有订单
        //根据用户uid获取用户的订单列表
        List<Orders> orderList = orderService.findUserOrdersByUid(user.getUid());
        model.addAttribute("orderList", orderList);
        //分页
        Page page = new Page();
        page.setLimit(4);
        page.setTotal(orderList.size());

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", page.getTotalPage());
        return "order_list";
    }

    /**
     * 管理订单
     * 订单状态
     * 0：未支付,去付款，
     * 1：已付款,催单，
     * 2：确定收货，
     * 4：订单完成，查看订单详情
     * 5：订单已取消
     * @param state
     * @param oid
     * @return
     */
    @RequestMapping("/manageOrder")
    public String ManageOrderServlet(int state, String oid, Model model){
        //获取用户信息
        User user = (User) session.getAttribute("user");
        if (user == null){
            //未登录，去登录
            return "redirect:login.jsp";
        }
        //更改数据库订单状态
        Orders  orders = orderService.getOrdersByOid(oid);
        switch (OrderStateEnum.getByState(state)) {
            case ORDER_NOT_PAY:
                //未支付，去支付
                return "redirect:toAccount?&oid="+oid;
            case ORDER_PAT_SUCCESS:
                //订单已支付，去催单
                //将状态改为确定收货
                orders.setState(2);
                orders.setOrdertime(new Date());
                orderService.updateOrders(orders);
                break;
            case ORDER_CONFIRM:
                //确定收货
                //更改数据库订单状态
                // 订单完成后是未评价的状态
                orders.setState(4);
                orders.setOrdertime(new Date());
                orderService.updateOrders(orders);
                break;
            case ORDER_COMPLETE:
                //订单完成，查看订单详情
                model.addAttribute("order",orders);
                return "order";
            default:
                System.out.println("无效参数");
        }
        return "redirect:orderList?currentPage=1";
    }

    /**
     * 通过订单去付款
     * @param oid
     * @param model
     * @return
     */
    @RequestMapping(value = "/toAccount")
    public String toAccountServlet(String oid, Model model){
        //获取用户信息
        User user = (User) session.getAttribute("user");
        if (user == null){
            //未登录，去登录
            return "redirect:login.jsp";
        }
        Orders orders = orderService.getOrdersByOid(oid);
        model.addAttribute("order",orders);

        return "account";
    }

    /**
     * 支付
     * 支付密码暂时用用户密码，用户密码已经进行md5加密
     * @param oid
     * @param password
     * @param model
     * @return
     */
    @RequestMapping(value = "/pay")
    public String payServlet(String oid, String password, Model model){
        //结算，用户支付

        //获取用户信息
        User user = (User) session.getAttribute("user");
        if (user == null){
            //未登录，去登录
            return "redirect:login.jsp";
        }

        // 通过订单编号去查询出订单信息
        Orders orders = orderService.getOrdersByOid(oid);

        //表示用户付款了,且密码正确，更改订单状态为1
        if(DigestUtils.md5Hex(password).equals(user.getPassword())){
            orders.setState(1);
            orders.setOrdertime(new Date());
            orderService.updateOrders(orders);
            return "redirect:orderList?currentPage=1";
        }else if (Objects.equals(password, "")){
            //如果密码为空，提示用户付款
            model.addAttribute("error", "请输入付款密码");
        }else if(!DigestUtils.md5Hex(password).equals(user.getPassword())){
            //密码错误
            model.addAttribute("error", "密码错误，请重新输入");
        }
        return "redirect:toAccount?oid="+oid;
    }

    @RequestMapping("/assessOrder")
    public String AssessOrderServlet(String oid, String assess, Model model){
        // 评价
        // 通过订单编号去查询出订单信息
        Orders orders = orderService.getOrdersByOid(oid);
        model.addAttribute("order",orders);

        if (!Objects.equals(assess, "") && !Objects.equals(assess, null)){
            //更新评价
            orders.setAssess(assess);
            orders.setOrdertime(new Date());
            orderService.updateOrders(orders);
            return "redirect:orderList?currentPage=1";
        }
        return "assess";
    }

}
