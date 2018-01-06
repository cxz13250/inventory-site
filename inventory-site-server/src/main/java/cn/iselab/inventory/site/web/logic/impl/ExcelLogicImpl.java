package cn.iselab.inventory.site.web.logic.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.iselab.inventory.site.model.ManageInfo;
import cn.iselab.inventory.site.service.ManageService;
import cn.iselab.inventory.site.web.logic.ExcelLogic;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:40 2018/1/7
 * @Modified By:
 */
@Service
public class ExcelLogicImpl implements ExcelLogic {

    @Autowired
    ManageService manageService;

    @Override
    public void getExcelForManageInfo(long id, String fileName,HttpServletResponse response){
        ManageInfo manageInfo=manageService.getManageInfo(id);

        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell11 = row1.createCell(0);
        cell11.setCellValue("收入类");

        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell21 = row2.createCell(0);
        cell21.setCellValue("销售类收入");
        HSSFCell cell22 = row2.createCell(1);
        cell22.setCellValue(manageInfo.getSaleIncome());

        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell31 = row3.createCell(0);
        cell31.setCellValue("销售类折让");
        HSSFCell cell32 = row3.createCell(1);
        cell32.setCellValue(manageInfo.getSaleDiscount());

        HSSFRow row4 = sheet.createRow(3);
        HSSFCell cell41 = row4.createCell(0);
        cell41.setCellValue("商品类收入");
        HSSFCell cell42 = row4.createCell(1);
        cell42.setCellValue(manageInfo.getGoodsIncome());

        HSSFRow row5 = sheet.createRow(4);
        HSSFCell cell51 = row5.createCell(0);
        cell51.setCellValue("商品类折让");
        HSSFCell cell52 = row5.createCell(1);
        cell52.setCellValue(manageInfo.getGoodsDiscount());

        HSSFRow row6 = sheet.createRow(5);
        HSSFCell cell61 = row6.createCell(0);
        cell61.setCellValue("总收入");
        HSSFCell cell62 = row6.createCell(1);
        cell62.setCellValue(manageInfo.getGoodsIncome()+manageInfo.getSaleIncome());

        HSSFRow row7 = sheet.createRow(6);
        HSSFCell cell71 = row7.createCell(0);
        cell71.setCellValue("支出类");

        HSSFRow row8 = sheet.createRow(7);
        HSSFCell cell81 = row8.createCell(0);
        cell81.setCellValue("销售成本");
        HSSFCell cell82 = row8.createCell(1);
        cell82.setCellValue(manageInfo.getSaleCost());

        HSSFRow row9 = sheet.createRow(8);
        HSSFCell cell91 = row9.createCell(0);
        cell91.setCellValue("商品类支出");
        HSSFCell cell92 = row9.createCell(1);
        cell92.setCellValue(manageInfo.getGoodsCost());

        HSSFRow row10 = sheet.createRow(9);
        HSSFCell cell101 = row10.createCell(0);
        cell101.setCellValue("总支出");
        HSSFCell cell102 = row10.createCell(1);
        cell102.setCellValue(manageInfo.getSaleCost());

        HSSFRow row11 = sheet.createRow(10);
        HSSFCell cel111 = row11.createCell(0);
        cel111.setCellValue("总利润");
        HSSFCell cell112 = row11.createCell(1);
        cell112.setCellValue(manageInfo.getProfit());

        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
