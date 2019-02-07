package com.jiacloud.api.controller;

import com.jiacloud.api.domain.CampusActivity;
import com.jiacloud.api.domain.Item;
import com.jiacloud.api.service.impl.CampusActivityServiceImpl;
import com.jiacloud.api.service.impl.ItemServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.spi.FileSystemProvider;
import java.security.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CampusActivityController {
    @Autowired
    private CampusActivityServiceImpl campusActivityService;
    @Autowired
    private ItemServiceImpl itemService;


    String dbName=null;

    /**参加活动**/
    @CrossOrigin
    @RequestMapping(value = "/joinActivity",method = RequestMethod.POST)
    public CampusActivity joinActivity(@RequestBody CampusActivity campusActivity){
        /**测试是否传递参数**/
//        System.out.println(campusActivity.getBdname());
//        System.out.println(campusActivity.getClassroom());

        /**接受参数**/
        String dbname=campusActivity.getBdname();
        String classroom=campusActivity.getClassroom();
        String name=campusActivity.getName();
        String number=campusActivity.getNumber();


        /**判断是否已经超时**/

        /**获取截至时间**/
        String activityName=itemService.findItemName(dbname).getName();
        String activityDeadline=itemService.findItem(activityName).getDeadline();
        System.out.println(activityDeadline);
        /**获取当前的时间**/
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        String currentTime=sdf.format(d);

        /**判断是否已经超时**/
        if(currentTime.compareTo(activityDeadline)>0){
            campusActivity.setCode(401);
            return campusActivity;
        }

        /**判断人数是否已经满了**/
            /**预定人数**/
            String Participants=itemService.findItemParticipants(dbname).getParticipants();
            int Participant=Integer.parseInt(Participants);
            /**当前人数**/
            int numberOfpeople=campusActivityService.countUpActivity(dbname);

            if(numberOfpeople>=Participant){
                /**如果人数已经满了 则返回值为400**/
                campusActivity.setCode(400);
                return campusActivity;
            }



        campusActivityService.joinActivity(dbname,classroom,name,number);

        campusActivity.setCode(200);

        return campusActivity;
    }
    /**返回参加人员信息**/
    @CrossOrigin
    @RequestMapping(value = "/findActivity",method = RequestMethod.POST)
    public List<CampusActivity> findActivity(@RequestBody CampusActivity campusActivity){

        dbName=campusActivity.getBdname();
        System.out.println(dbName);

        List<CampusActivity> campusActivities=campusActivityService.findAllActivity(dbName);

        return campusActivities;
    }

    @CrossOrigin
    @RequestMapping(value = "/getActivityExcel", method = RequestMethod.GET)
    public void getExcel(HttpServletResponse response) throws IOException {



        System.out.println(dbName);

        //创建Excel
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建一个Excel表单,参数为sheet的名字
        HSSFSheet sheet=workbook.createSheet("信息表");

        //创建表头
        setTitle(workbook,sheet);

        //获取项目数据库的值
        List<CampusActivity> campusActivities=campusActivityService.findAllActivity(dbName);

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for(CampusActivity campusActivity1:campusActivities){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(campusActivity1.getClassroom()); //获取班级
            row.createCell(1).setCellValue(campusActivity1.getName()); //获取人名
            row.createCell(2).setCellValue(campusActivity1.getNumber());  //获取学号
            rowNum++;
        }

//        String fileName="sports.xls";
          String fileName=dbName+".xls";
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
        cell.setCellValue("班级");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("名字");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("学号");
        cell.setCellStyle(style);

    }
}
