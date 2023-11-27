package com.gec.service;

import com.gec.bean.User;

/**
 * @author 泽申（Ertha)
 * @date 2023/4/25
 * 佛祖保佑
 * 隆金加滑
 */
public interface UserService {
    User getUser(String username);
    int creatUser(User user);

    int modifyUserInfo(User user);

    User findUserByUid(String uid);

}
