package com.jiacloud.api.dao;

import com.jiacloud.api.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    /***
     * 发布项目
     * @param name
     * @param site
     * @param title
     * @param time
     * @param participants
     * @param particulars
     * @param sponsor
     * @param deadline
     * @return
     */
    Item addItem(@Param(value = "name") String name,@Param(value = "site") String site,@Param(value = "title") String title,@Param(value = "time") String time,@Param(value = "participants") String participants,@Param(value = "particulars") String particulars,@Param(value = "sponsor") String sponsor,@Param(value = "deadline") String deadline);

    /***
     * 查询项目
     * @param name
     * @return
     */
    Item findItem(String name);

    /**
     * 返回所有的项目
     * @return
     */
    List<Item> findAllItem();


    /**
     * 查找活动预定人数
     * @param name
     * @return
     */
    Item findItemParticipants(@Param(value = "name") String name);

    /**
     * 添加文件
     * @param DocName
     * @return
     */
    String addDocName(@Param(value = "DocName") String DocName,@Param(value = "name") String name);



}
