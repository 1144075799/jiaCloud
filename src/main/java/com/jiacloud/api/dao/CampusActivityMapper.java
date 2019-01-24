package com.jiacloud.api.dao;

import com.jiacloud.api.domain.CampusActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CampusActivityMapper {

    /**
     * 创建活动表
     * @param bdname
     * @return
     */
    CampusActivity setActivity(@Param(value = "bdname") String bdname);

}
