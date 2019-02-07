package com.jiacloud.api.dao;

import com.jiacloud.api.domain.CampusActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CampusActivityMapper {

    /**
     * 创建活动表
     * @param bdname
     * @return
     */
    CampusActivity setActivity(@Param(value = "bdname") String bdname);

    /**
     * 参加活动
     * @param bdname
     * @param classroom
     * @param name
     * @param number
     * @return
     */
    CampusActivity joinActivity(@Param(value = "bdname") String bdname,@Param(value = "classroom") String classroom,@Param(value = "name") String name,@Param(value = "number") String number);

    /**
     * 查询所有活动
     * @param bdname
     * @return
     */
    List<CampusActivity> findAllActivity(@Param(value = "bdname") String bdname);

    /**
     * 统计参与人数
     * @param bdname
     * @return
     */
    Integer countUpActivity(@Param(value = "bdname") String bdname);
}
