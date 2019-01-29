package com.jiacloud.api.util;

import com.jiacloud.api.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
public class FileUploadController  {

    @Autowired
    private ImageServiceImpl imageService;

    @CrossOrigin
    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        //设置文件的保存路径
        String path = "E:\\Spring\\data\\img\\";
        System.out.println(path);

        //文件命名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

        //判断文件是否是图片
        Map<String, String> map = new HashMap<>();
        String[] imagType = {".jpg", ".png", ".bmp", ".gif"};
        List<String> imageTyepLists = Arrays.asList(imagType);
        if (imageTyepLists.contains(extendName)) {
            File dir = new File(path, originalFilename);
            //并接图片路径
            String ImgPath=path+originalFilename;
            File filepath = new File(path);
            //创建存放图片的文件夹
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            //把图片放进文件夹中
            file.transferTo(dir);

            //把图片的路径写入数据库
            imageService.addImagePath(ImgPath);

            return "200";
        }
        return "400";
    }
}
