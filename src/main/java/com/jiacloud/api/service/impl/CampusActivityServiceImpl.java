package com.jiacloud.api.service.impl;

import com.jiacloud.api.dao.CampusActivityMapper;
import com.jiacloud.api.domain.CampusActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampusActivityServiceImpl implements CampusActivityMapper {
    @Autowired
    private CampusActivityMapper campusActivityMapper;

    /**创建校园活动表**/
    public CampusActivity setActivity(String bdname){
        return campusActivityMapper.setActivity(bdname);
    }
}
