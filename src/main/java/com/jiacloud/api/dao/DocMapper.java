package com.jiacloud.api.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DocMapper {
    /**
     * 添加文件路径
     * @param DocPath
     * @param alias
     * @return
     */
    String addDocPath(@Param(value = "DocPath") String DocPath,@Param(value = "alias") String alias);

    /**
     * 查找文件路径
     * @param alias
     * @return
     */
    String fileDocPath(@Param(value = "alias") String alias);
}
