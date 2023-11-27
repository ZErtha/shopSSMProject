package com.gec.controller;

import com.gec.bean.User;
import com.gec.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.UUID;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/25
 * 佛祖保佑
 * 隆金加滑
 */
@Controller
public class UserController {

    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping(value = "/login")
    public String LoginServlet(String username, String password, Model model){
        // 需要判断这个用户是否存在，如果用户存在就判断密码是否正确，如果用户不存在就让用户去注册

        User user = userService.getUser(username);
        //用户是否存在
        if (user == null){
            //用户不存在提醒用户去注册
            model.addAttribute("error", "账号不存在，请注册");
        } else if (!Objects.equals(DigestUtils.md5Hex(password), user.getPassword())) {
            //判断密码是否正确 将传入的密码通过md5加密和数据库进行比对
            //需要更改数据库密码password字段的长度为50
            model.addAttribute("error", "密码错误，请重新登录");
        }else{
            //如果正确，存储用户信息，登录成功，返回首页
            session.setAttribute("user",user);
            return "redirect:index";
        }
        return "login";
    }

    /**
     * 注销用户登录
     * @param session
     * @return
     */
    @RequestMapping(value = "/cancelUser")
    public String cancelUserServlet(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:index";
    }

    /**
     * 用户注册
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/register")
    public String RegisterServlet(User user, Model model){
        //登录和注册的密码涉及到网络安全，对密码的加密能够在一定程度上增加安全性
        //获取用户数据库信息
        User userN = userService.getUser(user.getUsername());
        if (userN != null){
            //如果用户名已经存在数据库
            model.addAttribute("error","用户名已存在");
        }
        //设置用户的uuid
        user.setUid(UUID.randomUUID().toString());
        //将用户密码进行md5加密
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        //将用户插入数据库
        int res = userService.creatUser(user);
        if (res>0){
            //注册成功
            session.setAttribute("user",user);
            return "redirect:login.jsp";
        }else{
            //数据库执行失败，注册失败
            model.addAttribute("error","用户注册失败");
        }
        return "register";
    }

    /**
     * 修改用户信息
     * @return
     */
    @RequestMapping(value = "/modifyUserInfo")
    public String UpdateUserInfoServlet(User user){
        System.out.println("修改用户信息："+user);
        //因为用户名不可修改，所以更新除了用户名以外的数据
        userService.modifyUserInfo(user);
        return "redirect:index";
    }
}
