package com.jiacloud.api.controller;

import com.jiacloud.api.domain.Competition;
import com.jiacloud.api.service.impl.CompetitionDetailsServiceImpl;
import com.jiacloud.api.service.impl.CompetitionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompetitionController {

    @Autowired
    private CompetitionServiceImpl competitionService;

    @Autowired
    private CompetitionDetailsServiceImpl competitionDetailsService;

    /**发布竞赛**/
    @CrossOrigin
    @RequestMapping(value = "/appendCompetition",method = RequestMethod.POST)
    public Competition appendActivity(@RequestBody Competition competition){

        /**获取前端传递的参数 测试**/
//        System.out.println(competition.getName());

        /**获取前端传递参数**/
        String name=competition.getName();
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

    @CrossOrigin
    @RequestMapping(value = "/getCompetitions",method = RequestMethod.GET)
    public List<Competition> getCompetitions(){
        List<Competition> competitions=competitionService.findAllCompetition();
        return competitions;
    }


}
