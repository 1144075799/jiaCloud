package com.jiacloud.api.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageMapper {
    String addImagePath(@Param(value = "ImgPath") String ImgPath);
}
