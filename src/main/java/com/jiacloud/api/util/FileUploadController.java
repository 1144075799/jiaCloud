package com.jiacloud.api.util;

import com.jiacloud.api.service.impl.DocServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FileUploadController {

    @Autowired
    private DocServiceImpl docService;

//    @CrossOrigin
//    @RequestMapping(value = "/uploadDoc", method = RequestMethod.POST)
//    /**文件上传类**/
//    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
//
//        //设置文件的保存路径
//        String path = "E:\\Spring\\data\\doc\\";
//        System.out.println(path);
//        //活动别名
//        String alias="fuwu";
//
//        //文件命名
//        String originalFilename = file.getOriginalFilename();
//        System.out.println(originalFilename);
//        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
//
//        //判断文件是否是文档
//        Map<String, String> map = new HashMap<>();
//        String[] imagType = {".doc",".docx"};
//        List<String> imageTyepLists = Arrays.asList(imagType);
//        if (imageTyepLists.contains(extendName)) {
//            File dir = new File(path, originalFilename);
//            //并接图片路径
//            String DocPath=path+originalFilename;
//            File filepath = new File(path);
//            //创建存放图片的文件夹
//            if (!filepath.exists()) {
//                filepath.mkdirs();
//            }
//            //把图片放进文件夹中
//            file.transferTo(dir);
//
//            //把文档的路径写入数据库
//            docService.addDocPath(originalFilename,alias);
//
//            return "200";
//        }
//        return "400";
//    }

    @CrossOrigin
    @RequestMapping(value = "/getDoc", method = RequestMethod.GET)
    public void getDoc(HttpServletRequest request,
                       HttpServletResponse response) throws UnsupportedEncodingException {
        //活动别名
        String alias = "fuwu";
        String fileName = docService.fileDocPath(alias);

        System.out.println(fileName);

        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            //设置文件路径

            String realPath = "E:\\Spring\\data\\doc\\";
            File file = new File(realPath, fileName);


            // 如果文件名存在，则进行下载
            if (file.exists()) {

                System.out.println("文件存在");

                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download the song successfully!");
                } catch (Exception e) {
                    System.out.println("Download the song failed!");
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
