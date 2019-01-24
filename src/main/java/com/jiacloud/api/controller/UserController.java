package com.jiacloud.api.controller;

import com.jiacloud.api.domain.User;
import com.jiacloud.api.service.impl.StudentServiceImpl;
import com.jiacloud.api.service.impl.UserServiceImpl;
import com.jiacloud.api.util.MD5Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @Autowired
    private UserServiceImpl userService;


    /***
     * 注册控制器
     * @param user
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public User register(@RequestBody User user){

        /**打印从前端收取的数据**/
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
        System.out.println(user.getType());

        /**接受类型参数**/
        String userName=user.getUserName();
        String passWord=user.getPassWord();
        String type=user.getType();

        /**对密码进行加密处理**/
        MD5Util md5Util=new MD5Util();
        String addPassWord=md5Util.encode(passWord);
        user.setPassWord(addPassWord);
        System.out.println(addPassWord);

        /**判断是否已经存在相同的用户名**/
        String checkPass=null;
        //可能出现空值  所以这里要抛出异常
        try {
            checkPass=userService.extractUser(userName).getPassWord();
        } catch (Exception e) {
            System.out.println("不存在相同的用户名");
        }
        if(checkPass!=null){
            /**用户已存在**/
            user.setCode(400);
            return user;
        }

        /***插入数据库*/
        userService.addUser(userName,addPassWord,type);
        user.setCode(200);
        return user;
    }

    /***
     * 登录控制器
     * @param user
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public User login(@RequestBody User user){
        /**生成JSON对象**/
        JSONObject result = new JSONObject();

        /**打印从前端收取的数据**/
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());

        /**接受参数**/
        String userName=user.getUserName();
        String passWord=user.getPassWord();

        /**MD5对密码进行校验**/
        MD5Util md5Util=new MD5Util();
        String checkPassWord=md5Util.encode(passWord);
        user.setPassWord(checkPassWord);


        /**获取数据库中的密码**/
        String dbPassWord= null;
        try {
            dbPassWord = userService.extractUser(userName).getPassWord();
        } catch (Exception e) {
            /**没有这个用户名返回500**/
            user.setCode(500);
            return user;
        }


        /**登录成功 返回200**/
        if(dbPassWord.equals(checkPassWord)){
            user=userService.extractUser(userName);
            user.setCode(200);
            return user;
        }else{
            /**密码错误返回400**/
            user.setCode(400);
            return user;
        }

    }


}
