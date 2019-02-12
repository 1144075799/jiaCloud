package com.jiacloud.api.controller;

import com.jiacloud.api.domain.Teacher;
import com.jiacloud.api.service.impl.TeacherServiceImpl;
import com.jiacloud.api.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherService;

    String name=null;
    @CrossOrigin
    @RequestMapping(value = "/teacherRegister",method = RequestMethod.POST)
    public Teacher studentRegister(@RequestBody Teacher teacher){
        /**打印前端参数**/
        System.out.println(teacher.getUserName());

        /**获取前端参数**/
        String userName=teacher.getUserName();
        String passWord=teacher.getPassWord();
        String type="0";
        name=teacher.getName();

        /**对密码进行加密处理**/
        MD5Util md5Util=new MD5Util();
        String addPassWord=md5Util.encode(passWord);
        teacher.setPassWord(addPassWord);
//        System.out.println(addPassWord);

        /**判断是否已存在此老师**/
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

    /**上传图片**/
    @CrossOrigin
    @RequestMapping(value = "/uploadTeacherImg", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        //设置文件的保存路径
        String path = "E:\\Spring\\data\\img\\";
        System.out.println(path);

        //文件命名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

        //判断文件是否是图片
        Map<String, String> map = new HashMap<>();
        String[] imagType = {".jpg", ".png", ".bmp", ".gif"};
        List<String> imageTyepLists = Arrays.asList(imagType);
        if (imageTyepLists.contains(extendName)) {
            File dir = new File(path, originalFilename);
            //并接图片路径
            String ImgPath=path+originalFilename;
            File filepath = new File(path);
            //创建存放图片的文件夹
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            //把图片放进文件夹中
            file.transferTo(dir);

            //把图片的路径写入数据库
//            teacherService.addImagePath(ImgPath);
              teacherService.addImagePath(ImgPath,name);
            return "200";
        }
        return "400";
    }
    /**查找所有老师**/
    @CrossOrigin
    @RequestMapping(value = "/teacherGather",method = RequestMethod.GET)
    public List<Teacher> studentGather(){
        List<Teacher> teacher=teacherService.findAllTeacher();
        return teacher;
    }

//    /**返回管理员基本信息**/
//    @CrossOrigin
//    @RequestMapping(value = "/teacherBase",method = RequestMethod.POST)
//    public Teacher teacherBase(@RequestBody Teacher teacher){
//        String userName=teacher.getUserName();
//
//        teacherService.findBaseTeacher(userName);
//
//        return teacher;
//    }
}
