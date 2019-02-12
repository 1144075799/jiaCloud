package com.jiacloud.api.service.impl;

import com.jiacloud.api.dao.CompetitionMapper;
import com.jiacloud.api.domain.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionMapper {
    @Autowired
    private CompetitionMapper competitionMapper;

    /**发布竞赛**/
    public Competition addCompetition(String name, String time, String deadline, String grade, String sponsor, String presentation){
        return competitionMapper.addCompetition(name,time,deadline,grade,sponsor,presentation);
    }

    /**查找竞赛**/
    public Competition findCompetition(String name){
        return competitionMapper.findCompetition(name);
    }

    /**返回所有竞赛**/
    public List<Competition> findAllCompetition(){
        return competitionMapper.findAllCompetition();
    }

    /**返回竞赛到期时间**/
    public Competition findCompetitionDeadline(String name){
        return competitionMapper.findCompetitionDeadline(name);
    }

    /**添加文件**/
    @Override
    public String addDocName(String DocName,String name){
        return competitionMapper.addDocName(DocName,name);
    }
}
