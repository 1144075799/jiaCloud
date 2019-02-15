package com.jiacloud.api.controller;

import com.jiacloud.api.domain.Competition;
import com.jiacloud.api.service.impl.CompetitionDetailsServiceImpl;
import com.jiacloud.api.service.impl.CompetitionServiceImpl;
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
public class CompetitionController {

    @Autowired
    private CompetitionServiceImpl competitionService;

    @Autowired
    private CompetitionDetailsServiceImpl competitionDetailsService;

    String name=null;

    /**发布竞赛**/
    @CrossOrigin
    @RequestMapping(value = "/appendCompetition",method = RequestMethod.POST)
    public Competition appendActivity(@RequestBody Competition competition){

        /**获取前端传递的参数 测试**/
//        System.out.println(competition.getName());

        /**获取前端传递参数**/
        name=competition.getName();
        String time=competition.getTime();
        String deadline=competition.getDeadline();
        String grade=competition.getGrade();
        String sponsor=competition.getSponsor();
        String presentation=competition.getPresentation();

        /**判断此竞赛是否已经发布**/
        String dbtime=null;
        try {
            dbtime=competitionService.findCompetition(name).getTime();
        } catch (Exception e) {
            System.out.println("竞赛不存在");
        }

        if (dbtime!=null){
            System.out.println("此竞赛已经存在");
            competition.setCode(400);
            return competition;
        }


        /**添加竞赛**/
        competitionService.addCompetition(name,time,deadline,grade,sponsor,presentation);

        competition.setCode(200);

        return competition;
    }

    /**结束项目**/
    @CrossOrigin
    @RequestMapping(value = "/finishCompetition",method = RequestMethod.POST)
    public Competition finishCompetition(@RequestBody Competition competition){
        /**获取前端的值**/
        String name=competition.getName();
        System.out.println(name);

        /**判断活动是否存在，若存在则删除活动**/
        String time=null;
        try {
            time=competitionService.findCompetition(name).getTime();
        } catch (Exception e) {
            competition.setCode(400);
            return competition;
        }
        competitionService.deleteCompetition(name);
        competitionService.deleteCompetitionMember(name);
        competition.setCode(200);
        return competition;
    }

    @CrossOrigin
    @RequestMapping(value = "/getCompetitions",method = RequestMethod.GET)
    public List<Competition> getCompetitions(){
        List<Competition> competitions=competitionService.findAllCompetition();
        return competitions;
    }

    @CrossOrigin
    @RequestMapping(value = "/uploadCompetitionsDoc", method = RequestMethod.POST)
    /**项目文件上传类**/
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        //设置文件的保存路径
        String path = "E:\\Spring\\data\\doc\\";
//        System.out.println(path);

        //文件命名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

        //判断文件是否是文档
        Map<String, String> map = new HashMap<>();
        String[] imagType = {".doc",".docx"};
        List<String> imageTyepLists = Arrays.asList(imagType);
        if (imageTyepLists.contains(extendName)) {
            File dir = new File(path, originalFilename);
//            //并接图片路径
//            String DocPath=path+originalFilename;
            File filepath = new File(path);
            //创建存放图片的文件夹
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            //把图片放进文件夹中
            file.transferTo(dir);

            //把文档的路径写入数据库
            competitionService.addDocName(originalFilename,name);


            return "200";
        }
        return "400";
    }


}
