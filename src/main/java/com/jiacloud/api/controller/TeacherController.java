package com.jiacloud.api.controller;

import com.jiacloud.api.domain.Teacher;
import com.jiacloud.api.service.impl.TeacherServiceImpl;
import com.jiacloud.api.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherService;

    @CrossOrigin
    @RequestMapping(value = "/teacherRegister",method = RequestMethod.POST)
    public Teacher studentRegister(@RequestBody Teacher teacher){
        /**打印前端参数**/
        System.out.println(teacher.getUserName());

        /**获取前端参数**/
        String userName=teacher.getUserName();
        String passWord=teacher.getUserName();
        String type="0";
        String name=teacher.getName();

        /**对密码进行加密处理**/
        MD5Util md5Util=new MD5Util();
        String addPassWord=md5Util.encode(passWord);
        teacher.setPassWord(addPassWord);
        System.out.println(addPassWord);

        /**判断是否已存在此学生**/
        String dbTeacher=null;
        try {
            dbTeacher=teacherService.findTeacher(name).getUserName();
        } catch (Exception e) {
            System.out.println("无存在相同的教师");
        }
        if(dbTeacher!=null){
            teacher.setCode(400);
            return teacher;
        }

        teacherService.addTeacher(userName,addPassWord,type,name);

        teacher.setCode(200);

        return teacher;
    }

    /**老师登录处理**/
    @CrossOrigin
    @RequestMapping(value = "/teacherLogin",method = RequestMethod.POST)
    public Teacher studentLogin(@RequestBody Teacher teacher){
        /**打印前端获取的参数**/
//        System.out.println(teacher.getUserName());
//        System.out.println(teacher.getPassWord());

        /**获取前端传递的值**/
        String userName=teacher.getUserName();
        String passWord=teacher.getPassWord();

        /**对密码进行加密处理**/
        MD5Util md5Util=new MD5Util();
        String addPassWord=md5Util.encode(passWord);
        teacher.setPassWord(addPassWord);
//        System.out.println(addPassWord);

        /**对密码进行对比**/
        String dbPassWord;
        try {
            dbPassWord=teacherService.checkTeacher(userName).getPassWord();
        } catch (Exception e) {
            /**不存在用户时**/
            teacher.setCode(500);
            return teacher;
        }

        if(dbPassWord.equals(addPassWord)){
            /**登录成功之后 返回基础的信息**/
            teacher=teacherService.findBaseTeacher(userName);
            teacher.setCode(200);
            return teacher;
        }else{
            /**密码错误的时候**/
            teacher.setCode(400);
            return teacher;
        }

    }

    /**查找所有老师**/
    @CrossOrigin
    @RequestMapping(value = "/teacherGather",method = RequestMethod.GET)
    public List<Teacher> studentGather(){
        List<Teacher> teacher=teacherService.findAllTeacher();
        return teacher;
    }
}
