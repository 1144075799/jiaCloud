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

    /**创建校园活动表**/
    public CampusActivity setActivity(String bdname){
        return campusActivityMapper.setActivity(bdname);
    }

    /**参加校园活动**/
    public CampusActivity joinActivity(String bdname,String classroom,String name,String number){
        return campusActivityMapper.joinActivity(bdname,classroom,name,number);
    }

    /**返回所有参加人的信息**/
    public List<CampusActivity> findAllActivity(String bdname){
        return campusActivityMapper.findAllActivity(bdname);
    }

    /**统计参与人数**/
    public Integer  countUpActivity(String bdname){
        Integer numberOfpeople=campusActivityMapper.countUpActivity(bdname);
        return numberOfpeople;
    }
}
