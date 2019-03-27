package com.jiacloud.api.controller;

import com.jiacloud.api.domain.Item;
import com.jiacloud.api.service.impl.CampusActivityServiceImpl;
import com.jiacloud.api.service.impl.DocServiceImpl;
import com.jiacloud.api.service.impl.ItemServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;
    @Autowired
    private CampusActivityServiceImpl campusActivityService;
    @Autowired
    private DocServiceImpl docService;

    String name=null;

    /**发布项目**/
    @CrossOrigin
    @RequestMapping(value = "/appendActivity",method = RequestMethod.POST)
    public Item appendActivity(@RequestBody Item item){
        /**打印从前端收取的数据(测试用)**/
//        System.out.println(item.getName());
//        System.out.println(item.getSite());

        /**获取前端传递的值**/
        name=item.getName();
        String site=item.getSite();
        String title=item.getTitle();
        String time=item.getTime();
        String participants=item.getParticipants();
        String particulars=item.getParticulars();
        String sponsor=item.getSponsor();
        String deadline=item.getDeadline();

        /**判断数据库钟是否已存在这个活动**/
        String checkTitle=null;
        try {
            checkTitle=itemService.findItem(name).getTitle();
        } catch (Exception e) {
            System.out.println("没有存在相同的项目");
        }
        if(checkTitle!=null){
            /**返回请求号**/
            item.setCode(401);
            return item;
        }

        /**插入数据库**/
        itemService.addItem(name,site,title,time,participants,particulars,sponsor,deadline);


        /**返回请求号**/
        item.setCode(200);

        return item;
    }
    /**获取活动**/
    @CrossOrigin
    @RequestMapping(value = "/getActivity",method = RequestMethod.GET)
    public List<Item> getActivity(){
        List<Item> item=itemService.findAllItem();
        return item;
    }

    /**结束项目**/
    @CrossOrigin
    @RequestMapping(value = "/finishActivity",method = RequestMethod.POST)
    public Item finishActivity(@RequestBody Item item){
        /**获取前端的值**/
        String name=item.getName();
        System.out.println(name);

        /**判断活动是否存在，若存在则删除活动**/
        String title=null;
        try {
            title=itemService.findItem(name).getTitle();
        } catch (Exception e) {
            item.setCode(400);
            return item;
        }
        itemService.deleteItem(name);
        itemService.deleteItemMember(name);
        item.setCode(200);
        return item;
    }

    /**查找活动**/
    @CrossOrigin
    @RequestMapping(value = "/findFuzzyActivity",method = RequestMethod.POST)
    public Item findFuzzyActivity(@RequestBody Item item){
        /**获取前端的参数**/
        String fuzzyName=item.getFuzzyName();


        /**判断数据库钟是否已存在这个活动**/
        String checkTitle=null;
        try {
            checkTitle=itemService.findFuzzyItem(fuzzyName).getTitle();
        } catch (Exception e) {
            item.setCode(400);
            return item;
        }

        /**获取指定的项目**/
        item=itemService.findFuzzyItem(fuzzyName);
        item.setCode(200);

        return item;
    }



    @CrossOrigin
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    /**项目图片上传类**/
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        //设置文件的保存路径
        String path = "E:\\Spring\\data\\img\\";
//        System.out.println(path);

        //文件命名
        String originalFilename = file.getOriginalFilename();
        //System.out.println(originalFilename);
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

        //判断文件是否是文档
        Map<String, String> map = new HashMap<>();
        String[] imagType = {".jpg", ".png", ".bmp", ".gif"};
        List<String> imageTyepLists = Arrays.asList(imagType);
        if (imageTyepLists.contains(extendName)) {
            File dir = new File(path, originalFilename);
            //并接图片路径
            String DocPath=path+originalFilename;
            File filepath = new File(path);
            //创建存放图片的文件夹
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            //把图片放进文件夹中
            file.transferTo(dir);

            //把文档的路径写入数据库
            itemService.addImagePath(DocPath,name);

            return "200";
        }
        return "400";
    }


    @CrossOrigin
    @RequestMapping(value = "/uploadDoc", method = RequestMethod.POST)
    /**项目文件上传类**/
    public String uploadDoc(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        //设置文件的保存路径
        String path = "E:\\Spring\\data\\doc\\";
//        System.out.println(path);

        //文件命名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

        //判断文件是否是文档
        Map<String, String> map = new HashMap<>();
        String[] imagType = {".doc",".docx"};
        List<String> imageTyepLists = Arrays.asList(imagType);
        if (imageTyepLists.contains(extendName)) {
            File dir = new File(path, originalFilename);
//            //并接图片路径
//            String DocPath=path+originalFilename;
            File filepath = new File(path);
            //创建存放图片的文件夹
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            //把图片放进文件夹中
            file.transferTo(dir);

            //把文档的路径写入数据库
//            docService.addDocPath(originalFilename,name);
            itemService.addDocName(originalFilename,name);

            return "200";
        }
        return "400";
    }

    /**生成excel**/
    @CrossOrigin
    @RequestMapping(value = "/getExcel", method = RequestMethod.GET)
    public void getExcel(HttpServletResponse response) throws IOException {
        //创建Excel
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建一个Excel表单,参数为sheet的名字
        HSSFSheet sheet=workbook.createSheet("项目表");

        //创建表头
        setTitle(workbook,sheet);

        //获取项目数据库的值
        List<Item> items=itemService.findAllItem();

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for(Item item:items){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(item.getName()); //获取活动名字
            row.createCell(1).setCellValue(item.getSite()); //获取活动地点
            row.createCell(2).setCellValue(item.getTitle());  //获取活动主题
            row.createCell(3).setCellValue(item.getTitle());  //获取活动时间
            row.createCell(4).setCellValue(item.getParticipants()); //获取活动人数
            row.createCell(5).setCellValue(item.getParticulars());  //获取活动介绍
            row.createCell(6).setCellValue(item.getSponsor());  //获取活动举办单位
            rowNum++;
        }
        String fileName="Activity.xls";

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());


    }

    private void setTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 10*256);
        sheet.setColumnWidth(1, 20*256);
        sheet.setColumnWidth(2, 20*256);
        sheet.setColumnWidth(3, 100*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("名字");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("地点");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("主题");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("时间");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("人数");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("介绍");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("单位");
        cell.setCellStyle(style);
    }
}
