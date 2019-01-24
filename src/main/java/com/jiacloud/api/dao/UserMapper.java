package com.jiacloud.api.dao;

import com.jiacloud.api.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    /****
     * 添加用户
     * @param userName
     * @param passWord
     * @param type
     * @return
     */
    User addUser(@Param(value="userName") String userName,@Param(value = "passWord") String passWord,@Param(value = "type") String type);

    /***
     * 查找用户
     * @param userName
     * @return
     */
    User extractUser(String userName);
}
