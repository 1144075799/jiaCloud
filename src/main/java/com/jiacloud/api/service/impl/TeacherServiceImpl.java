package com.jiacloud.api.service.impl;

import com.jiacloud.api.dao.TeacherMapper;
import com.jiacloud.api.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherMapper {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher addTeacher(String userName,String passWord,String type,String name){
        return  teacherMapper.addTeacher(userName,passWord,type,name);
    }

    @Override
    public Teacher findTeacher(String name){
        return teacherMapper.findTeacher(name);
    }

    @Override
    public Teacher checkTeacher(String userName){
        return teacherMapper.checkTeacher(userName);
    }

    @Override
    public Teacher findBaseTeacher(String userName){
        return teacherMapper.findBaseTeacher(userName);
    }
}
