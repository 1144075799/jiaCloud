package com.jiacloud.api.dao;

import com.jiacloud.api.domain.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherMapper {
    /**
     * 添加老师
     * @param userName
     * @param passWord
     * @param type
     * @param name
     * @return
     */
    Teacher addTeacher(@Param(value="userName") String userName, @Param(value = "passWord") String passWord,@Param(value="type") String type,@Param(value = "name") String name);

    /**
     * 查找老师
     * @param name
     * @return
     */
    Teacher findTeacher(String name);

    /**
     * 获取密码
     * @param userName
     * @return
     */
    Teacher checkTeacher(String userName);

    /**
     *返回基本信息
     * @param userName
     * @return
     */
    Teacher findBaseTeacher(String userName);

    /**
     * 查找所有的管理员
     * @return
     */
    List<Teacher> findAllTeacher();

}
