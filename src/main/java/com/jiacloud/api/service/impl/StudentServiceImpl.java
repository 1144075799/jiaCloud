package com.jiacloud.api.service.impl;

import com.jiacloud.api.dao.StudentMapper;
import com.jiacloud.api.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentMapper {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student addStudent(String userName, String passWord, String name, String number, String classroom){
        return studentMapper.addStudent(userName,passWord,name,number,classroom);
    }

    @Override
    public Student findStudent(String name){
        return studentMapper.findStudent(name);
    }

    @Override
    public Student checkStudent(String userName){
        return studentMapper.checkStudent(userName);
    }

    @Override
    public Student findBaseStudent(String userName){
        return studentMapper.findBaseStudent(userName);
    }

    @Override
    public List<Student> findAllStudent(){
        return studentMapper.findAllStudent();
    }

    @Override
    public Student deleteStudent(String name){
        return studentMapper.deleteStudent(name);
    }

    @Override
    public Student alterStudent(String id,String name,String number,String classroom){
        return studentMapper.alterStudent(id,name,number,classroom);
    }
}
