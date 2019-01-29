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
     * @param alias
     * @return
     */
    Item addItem(@Param(value = "name") String name,@Param(value = "site") String site,@Param(value = "title") String title,@Param(value = "time") String time,@Param(value = "participants") String participants,@Param(value = "particulars") String particulars,@Param(value = "sponsor") String sponsor,@Param(value = "deadline") String deadline,@Param(value = "alias") String alias);

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
     * 查找活动名称
     * @param alias
     * @return
     */
    Item findItemName(String alias);



}
