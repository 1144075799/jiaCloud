package com.jiacloud.api.service.impl;

import com.jiacloud.api.dao.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageMapper{
    @Autowired
    ImageMapper imageMapper;

    @Override
    public String addImagePath(String ImgPath){
        return imageMapper.addImagePath(ImgPath);
    }
}
