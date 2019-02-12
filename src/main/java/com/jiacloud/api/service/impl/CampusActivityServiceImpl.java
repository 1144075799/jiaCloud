package com.jiacloud.api.service.impl;

import com.jiacloud.api.dao.CampusActivityMapper;
import com.jiacloud.api.domain.CampusActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusActivityServiceImpl implements CampusActivityMapper {
    @Autowired
    private CampusActivityMapper campusActivityMapper;


    /**参加校园活动**/
    public CampusActivity joinActivity(String activityName,String classroom,String name,String number){
        return campusActivityMapper.joinActivity(activityName,classroom,name,number);
    }

    /**返回所有参加人的信息**/
    public List<CampusActivity> findAllActivity(String activityName){
        return campusActivityMapper.findAllActivity(activityName);
    }

    /**统计参与人数**/
    public Integer  countUpActivity(String activityName){
        Integer numberOfpeople=campusActivityMapper.countUpActivity(activityName);
        return numberOfpeople;
    }
}
