package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.web.logic.ExcelLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:37 2018/1/7
 * @Modified By:
 */
@RestController
public class ExcelController extends BaseController{

    @Autowired
    ExcelLogic excelLogic;

    @RequestMapping(value = UrlConstants.API_EXCEL+"/info",method = RequestMethod.GET)
    public void ExcelManageInfo(@RequestParam(value = "id")Long id,
                                @RequestParam(value = "fileName")String fileName,
                                HttpServletResponse response)throws Exception{
        excelLogic.getExcelForManageInfo(id,fileName,response);
    }

    @RequestMapping(value = UrlConstants.API_EXCEL+"/detail",method = RequestMethod.GET)
    public void ExcelSaleDetail(@RequestParam(value = "fileName")String fileName,
                                HttpServletRequest request,
                                HttpServletResponse response){
        excelLogic.getExcelForSaleDetail(fileName,request,response);
    }
}
