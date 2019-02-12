package com.jiacloud.api.dao;

import com.jiacloud.api.domain.CampusActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CampusActivityMapper {



    /**
     * 参加活动
     * @param activityName
     * @param classroom
     * @param name
     * @param number
     * @return
     */
    CampusActivity joinActivity(@Param(value = "activityName") String activityName,@Param(value = "classroom") String classroom,@Param(value = "name") String name,@Param(value = "number") String number);

    /**
     * 查询所有活动
     * @param activityName
     * @return
     */
    List<CampusActivity> findAllActivity(@Param(value = "activityName") String activityName);

    /**
     * 统计参与人数
     * @param activityName
     * @return
     */
    Integer countUpActivity(@Param(value = "activityName") String activityName);
}
