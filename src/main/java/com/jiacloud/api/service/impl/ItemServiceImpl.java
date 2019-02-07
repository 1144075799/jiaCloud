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
    public Item addItem(String name,String site,String title,String time,String participants,String particulars,String sponsor,String deadline,String alias){
        return itemMapper.addItem(name,site,title,time,participants,particulars,sponsor,deadline,alias);
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

    /**查找活动名称**/
    @Override
    public Item findItemName(String alias){
        return itemMapper.findItemName(alias);
    }

    /**查找活动预定人数**/
    @Override
    public Item findItemParticipants(String alias){
        return itemMapper.findItemParticipants(alias);
    }
}
