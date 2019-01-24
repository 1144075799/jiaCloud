package com.jiacloud.api.service.impl;

import com.jiacloud.api.dao.UserMapper;
import com.jiacloud.api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserMapper {

    @Autowired
    private UserMapper userMapper;

    /**添加用户**/
    @Override
    public User addUser(String userName,String passWord,String type){
        return userMapper.addUser(userName,passWord,type);
    }

    /**查找用户**/
    @Override
    public User extractUser(String userName){
        return userMapper.extractUser(userName);
    }

}
