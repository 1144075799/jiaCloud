package com.jiacloud.api.controller;

import com.jiacloud.api.domain.CompetitionDetails;
import com.jiacloud.api.service.impl.CompetitionDetailsServiceImpl;
import com.jiacloud.api.service.impl.CompetitionServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CompetitionDetailsController {

    @Autowired
    private CompetitionServiceImpl competitionService;

    @Autowired
    private CompetitionDetailsServiceImpl competitionDetailsService;

    String competitionName=null;

    /**参加竞赛**/
    @CrossOrigin
    @RequestMapping(value = "/joinCompetition",method = RequestMethod.POST)
    public CompetitionDetails joinActivity(@RequestBody CompetitionDetails competitionDetails){
        /**测试前端传递的参数**/
//        System.out.println(competitionDetails.getCaptain());

        /**接受前端传递的参数**/
        String captain=competitionDetails.getCaptain();
        String member=competitionDetails.getMember();
        String number=competitionDetails.getNumber();
        String QQ=competitionDetails.getQQ();
        String telephone=competitionDetails.getTelephone();
        String className=competitionDetails.getClassName();
        String teamName=competitionDetails.getTeamName();
        String remark=competitionDetails.getRemark();
        String competitionName=competitionDetails.getCompetitionName();

        /**判断是否已经超时**/

        /**获取截至时间**/
        String competitionDeadline=competitionService.findCompetitionDeadline(competitionName).getDeadline();
//        System.out.println(competitionDeadline);

        /**获取当前的时间**/
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        String currentTime=sdf.format(d);
//        System.out.println(currentTime);

        /**判断是否已经超时**/
        if(currentTime.compareTo(competitionDeadline)>0){
            competitionDetails.setCode(401);
            return competitionDetails;
        }

        competitionDetailsService.joinCompetitionDetails(captain,member,number,telephone,QQ,className,teamName,remark,competitionName);

        competitionDetails.setCode(200);

        return competitionDetails;
    }

    /**返回参加人员信息**/
    @CrossOrigin
    @RequestMapping(value = "/findCompetition",method = RequestMethod.POST)
    public List<CompetitionDetails> findActivity(@RequestBody CompetitionDetails competitionDetails){

        competitionName=competitionDetails.getCompetitionName();
//        System.out.println(dbName);

        List<CompetitionDetails> competitionDetailsList=competitionDetailsService.findCompetitionDetails(competitionName);

        return competitionDetailsList;
    }

    @CrossOrigin
    @RequestMapping(value = "/getCompetitionExcel", method = RequestMethod.GET)
    public void getExcel(HttpServletResponse response) throws IOException {

        //创建Excel
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建一个Excel表单,参数为sheet的名字
        HSSFSheet sheet=workbook.createSheet("信息表");

        //创建表头
        setTitle(workbook,sheet);

        //获取项目数据库的值
        List<CompetitionDetails> competitionDetailsList=competitionDetailsService.findCompetitionDetails(competitionName);

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for(CompetitionDetails competitionDetail:competitionDetailsList){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(competitionDetail.getCaptain()); //获取队长
            row.createCell(1).setCellValue(competitionDetail.getMember()); //获取队员
            row.createCell(2).setCellValue(competitionDetail.getNumber());  //获取学号
            row.createCell(3).setCellValue(competitionDetail.getTelephone());  //获取电话
            row.createCell(4).setCellValue(competitionDetail.getQQ());  //获取QQ
            row.createCell(5).setCellValue(competitionDetail.getClassName());  //获取班级
            row.createCell(6).setCellValue(competitionDetail.getTeamName());  //获取队名
            row.createCell(7).setCellValue(competitionDetail.getRemark());  //获取备注
            rowNum++;
        }

        String fileName=competitionName+".xls";
        String downloadFileName =new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + downloadFileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());


    }

    private void setTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 25*256);
        sheet.setColumnWidth(1, 50*256);
        sheet.setColumnWidth(2, 25*256);
        sheet.setColumnWidth(3, 25*256);
        sheet.setColumnWidth(4, 25*256);
        sheet.setColumnWidth(5, 25*256);
        sheet.setColumnWidth(6, 25*256);
        sheet.setColumnWidth(7, 25*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("队长");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("队员");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("学号");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("电话");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("QQ");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("班级");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("队名");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

    }
}
