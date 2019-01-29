package com.jiacloud.api.util;

import com.jiacloud.api.domain.Item;
import com.jiacloud.api.service.impl.ItemServiceImpl;
import org.apache.catalina.connector.Response;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@RestController
public class ExcelController{

    @Autowired
    private ItemServiceImpl itemService;


//    @CrossOrigin
//    @RequestMapping(value = "/getExcel", method = RequestMethod.GET)
//    public void getExcel(HttpServletResponse response) throws IOException {
//        //创建Excel
//        HSSFWorkbook workbook = new HSSFWorkbook();
//
//        //创建一个Excel表单,参数为sheet的名字
//        HSSFSheet sheet=workbook.createSheet("项目表");
//
//        //创建表头
//        setTitle(workbook,sheet);
//
//        //获取项目数据库的值
//        List<Item> items=itemService.findAllItem();
//
//        //新增数据行，并且设置单元格数据
//        int rowNum = 1;
//        for(Item item:items){
//            HSSFRow row = sheet.createRow(rowNum);
//            row.createCell(0).setCellValue(item.getName()); //获取活动名字
//            row.createCell(1).setCellValue(item.getSite()); //获取活动地点
//            row.createCell(2).setCellValue(item.getTitle());  //获取活动主题
//            row.createCell(3).setCellValue(item.getTitle());  //获取活动时间
//            row.createCell(4).setCellValue(item.getParticipants()); //获取活动人数
//            row.createCell(5).setCellValue(item.getParticulars());  //获取活动介绍
//            row.createCell(6).setCellValue(item.getSponsor());  //获取活动举办单位
//            rowNum++;
//        }
//        String fileName="Activity.xls";
//
//        //准备将Excel的输出流通过response输出到页面下载
//        //八进制输出流
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//        response.flushBuffer();
//        workbook.write(response.getOutputStream());
//
//
//    }
//
//    private void setTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
//        HSSFRow row = sheet.createRow(0);
//        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
//        sheet.setColumnWidth(0, 10*256);
//        sheet.setColumnWidth(1, 20*256);
//        sheet.setColumnWidth(2, 20*256);
//        sheet.setColumnWidth(3, 100*256);
//
//        //设置为居中加粗
//        HSSFCellStyle style = workbook.createCellStyle();
//        HSSFFont font = workbook.createFont();
//        font.setBold(true);
//        style.setFont(font);
//
//        HSSFCell cell;
//        cell = row.createCell(0);
//        cell.setCellValue("名字");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(1);
//        cell.setCellValue("地点");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(2);
//        cell.setCellValue("主题");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(3);
//        cell.setCellValue("时间");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(4);
//        cell.setCellValue("人数");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(5);
//        cell.setCellValue("介绍");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(6);
//        cell.setCellValue("单位");
//        cell.setCellStyle(style);
//    }
}
