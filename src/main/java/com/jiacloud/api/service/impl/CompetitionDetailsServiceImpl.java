package com.jiacloud.api.service.impl;

import com.jiacloud.api.dao.CompetitionDetailsMapper;
import com.jiacloud.api.domain.CompetitionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionDetailsServiceImpl implements CompetitionDetailsMapper {
    @Autowired
    private CompetitionDetailsMapper competitionDetailsMapper;

    /**参加竞赛**/
    public CompetitionDetails joinCompetitionDetails(String captain,String member,String number,String telephone,String QQ,String className,String teamName,String remark,String competitionName){
        return competitionDetailsMapper.joinCompetitionDetails(captain,member,number,telephone,QQ,className,teamName,remark,competitionName);
    }

    /**返回竞赛人员信息**/
    public List<CompetitionDetails> findCompetitionDetails(String competitionName){
        return competitionDetailsMapper.findCompetitionDetails(competitionName);
    }
}

