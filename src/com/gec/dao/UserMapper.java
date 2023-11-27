package com.gec.dao;

import com.gec.bean.User;

/**
* @author 泽申（Ertha)
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-24 21:57:45
* @Entity com.gec.bean.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String username);

}
