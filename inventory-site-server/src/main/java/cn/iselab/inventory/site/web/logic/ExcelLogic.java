package cn.iselab.inventory.site.web.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:39 2018/1/7
 * @Modified By:
 */
public interface ExcelLogic {

    void getExcelForManageInfo(long id, String fileName, HttpServletResponse response);

    void getExcelForSaleDetail(String fileName, HttpServletRequest request, HttpServletResponse response);
}
