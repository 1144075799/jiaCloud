package com.jiacloud.api.controller;

import com.jiacloud.api.domain.Student;
import com.jiacloud.api.service.impl.StudentServiceImpl;
import com.jiacloud.api.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    /**学生注册处理**/
    @CrossOrigin
    @RequestMapping(value = "/studentRegister",method = RequestMethod.POST)
    public Student studentRegister(@RequestBody Student student){
        /**打印从前端收取的数据**/
        System.out.println(student.getUserName());
        System.out.println(student.getPassWord());

        /**获取前端传递的值**/
        String userName=student.getUserName();
        String passWord=student.getPassWord();
        String name=student.getName();
        String number=student.getNumber();
        String classroom=student.getClassroom();

        /**对密码进行加密处理**/
        MD5Util md5Util=new MD5Util();
        String addPassWord=md5Util.encode(passWord);
        student.setPassWord(addPassWord);
        System.out.println(addPassWord);

        /**判断是否已存在此学生**/
        String dbStudent=null;
        try {
            dbStudent=studentService.findStudent(name).getNumber();
        } catch (Exception e) {
            System.out.println("无存在相同的学生");
        }
        if(dbStudent!=null){
            student.setCode(400);
            return student;
        }

        studentService.addStudent(userName,addPassWord,name,number,classroom);

        student.setCode(200);

        return student;
    }
    /**学生登录处理**/
    @CrossOrigin
    @RequestMapping(value = "/studentLogin",method = RequestMethod.POST)
    public Student studentLogin(@RequestBody Student student){
        /**打印前端获取的参数**/
//        System.out.println(student.getUserName());
//        System.out.println(student.getPassWord());

        /**获取前端传递的值**/
        String userName=student.getUserName();
        String passWord=student.getPassWord();

        /**对密码进行加密处理**/
        MD5Util md5Util=new MD5Util();
        String addPassWord=md5Util.encode(passWord);
        student.setPassWord(addPassWord);
//        System.out.println(addPassWord);

        /**对密码进行对比**/
        String dbPassWord;
        try {
            dbPassWord=studentService.checkStudent(userName).getPassWord();
        } catch (Exception e) {
            /**不存在用户时**/
            student.setCode(500);
            return student;
        }

        if(dbPassWord.equals(addPassWord)){
            /**登录成功之后 返回基础的信息**/
            student=studentService.findBaseStudent(userName);
            student.setCode(200);
            return student;
        }else{
            /**密码错误的时候**/
            student.setCode(400);
            return student;
        }

    }
    /**查找所有学生**/
    @CrossOrigin
    @RequestMapping(value = "/studentGather",method = RequestMethod.GET)
    public List<Student> studentGather(){
        List<Student> students=studentService.findAllStudent();
        return students;
    }
    /**删除指定学生**/
    @CrossOrigin
    @RequestMapping(value = "/studentDelete",method = RequestMethod.POST)
    public Student studentDelete(@RequestBody Student student){
        /**获取要删除的对象**/
        String name=student.getName();

        System.out.println(name);

        /**判断是否有那个学生**/
        String userName=null;
        try {
            userName=studentService.deleteStudent(name).getName();
        } catch (Exception e) {
            /**不存在用户时**/
            student.setCode(500);
            return student;
        }

        student.setCode(200);

        return student;
    }

    /**修改指定学生**/
    @CrossOrigin
    @RequestMapping(value = "/studentAlter",method = RequestMethod.POST)
    public Student studentAlter(@RequestBody Student student){
        /**获取前端的参数(测试)**/
        System.out.println(student.getId());

        /**获取前端的参数**/
        int id=student.getId();
        String studentId=Integer.toString(id);
        String name=student.getName();
        String number=student.getNumber();
        String classroom=student.getClassroom();

        /**修改此学生**/

        try {
            studentService.alterStudent(studentId,name,number,classroom);
        } catch (Exception e) {
            /**此学生不存在**/
            student.setCode(500);
            return student;
        }

        student.setCode(200);
        return student;
    }
}
