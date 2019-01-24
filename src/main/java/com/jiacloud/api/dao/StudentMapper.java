package com.jiacloud.api.dao;

import com.jiacloud.api.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 添加学生账号
     * @param userName
     * @param passWord
     * @param name
     * @param number
     * @param classroom
     * @return
     */
    Student addStudent(@Param(value="userName") String userName,@Param(value = "passWord") String passWord,@Param(value = "name") String name,@Param(value = "number") String number,@Param(value = "classroom") String classroom);

    /***
     * 找学生
     * @param name
     * @return
     */
    Student findStudent(String name);

    /**
     * 检验密码
     * @param userName
     * @return
     */
    Student checkStudent(String userName);

    /**
     * 查找基本信息
     * @param userName
     * @return
     */
    Student findBaseStudent(String userName);

    /**
     * 查找所有的学生
     * @return
     */
    List<Student> findAllStudent();

    /**
     * 删除指定学生
     * @param name
     * @return
     */
    Student deleteStudent(String name);

    Student alterStudent(@Param(value = "id") String id,@Param(value = "name") String name,@Param(value = "number") String number,@Param(value = "classroom") String classroom);
}
