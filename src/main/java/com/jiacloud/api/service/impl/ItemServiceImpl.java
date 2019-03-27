package com.jiacloud.api.service.impl;

import com.jiacloud.api.dao.ItemMapper;
import com.jiacloud.api.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemMapper{
    @Autowired
    private ItemMapper itemMapper;

    /**发布项目**/
    @Override
    public Item addItem(String name,String site,String title,String time,String participants,String particulars,String sponsor,String deadline){
        return itemMapper.addItem(name,site,title,time,participants,particulars,sponsor,deadline);
    }

    /**查找项目**/
    @Override
    public Item findItem(String name){
        return itemMapper.findItem(name);
    }

    /**返回所有的项目**/
    @Override
    public List<Item> findAllItem(){
        return itemMapper.findAllItem();
    }


    /**查找活动预定人数**/
    @Override
    public Item findItemParticipants(String name){
        return itemMapper.findItemParticipants(name);
    }

    /**添加文件**/
    @Override
    public String addDocName(String DocName,String name){
        return itemMapper.addDocName(DocName,name);
    }

    /**添加图片**/
    @Override
    public String addImagePath(String imgPath,String name){
        return itemMapper.addImagePath(imgPath,name);
    }

    /**模糊查找**/
    @Override
    public Item findFuzzyItem(String fuzzyName){
        return itemMapper.findFuzzyItem(fuzzyName);
    }

    /**删除活动**/
    @Override
    public Item deleteItem(String name){
        return itemMapper.deleteItem(name);
    }

    /**删除活动相关人员**/
    @Override
    public Item deleteItemMember(String name){
        return itemMapper.deleteItemMember(name);
    }
}
