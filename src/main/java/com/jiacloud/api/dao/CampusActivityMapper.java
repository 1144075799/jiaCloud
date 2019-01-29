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

    CampusActivity joinActivity(@Param(value = "bdname") String bdname,@Param(value = "classroom") String classroom,@Param(value = "name") String name,@Param(value = "number") String number);

    List<CampusActivity> findAllActivity(@Param(value = "bdname") String bdname);
}
