package com.jiacloud.api.service.impl;

import com.jiacloud.api.dao.DocMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocServiceImpl implements DocMapper {
    @Autowired
    DocMapper docMapper;

    /*
    添加文件路径
     */
    @Override
    public String addDocPath(String DocPath,String alias){
        return docMapper.addDocPath(DocPath,alias);
    }

    @Override
    public String fileDocPath(String alias){
        return docMapper.fileDocPath(alias);
    }
}
