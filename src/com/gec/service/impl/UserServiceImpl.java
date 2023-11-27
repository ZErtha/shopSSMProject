package com.gec.service.impl;

import com.gec.bean.User;
import com.gec.dao.UserMapper;
import com.gec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/25
 * 佛祖保佑
 * 隆金加滑
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 获取用户
     * @param username
     * @return
     */
    @Override
    public User getUser(String username) {
        return userMapper.selectByUserName(username);
    }

    /**
     * 将用户信息插入用户表中
     * @param user
     * @return
     */
    @Override
    public int creatUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int modifyUserInfo(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User findUserByUid(String uid) {
        return userMapper.selectByPrimaryKey(uid);
    }
}
