package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.web.data.SaleDetailVO;
import cn.iselab.inventory.site.web.data.SaleStrategyVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.SaleDetailLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:36 2017/12/8
 * @Modified By:
 */
@RestController
public class SaleDetailController extends BaseController {

    @Autowired
    SaleDetailLogic saleDetailLogic;

    @RequestMapping(value = UrlConstants.API_SALEDETAIL,method = RequestMethod.GET)
    public Map<String,Object> getSaleDetail(@RequestParam(name = "startTime")Long startTime,
                                            @RequestParam(name = "endTime")Long endTime,
                                            @RequestParam(name = "sortBy")String sortBy,
                                            @RequestParam(name = "goodName",required = false)String goodName,
                                            HttpServletRequest request)throws Exception{
        try {
            String activePage = request.getHeader("activePage");
            String rowsOnPage = request.getHeader("rowsOnPage");
            if(activePage == null || rowsOnPage == null) {
                throw new IllegalArgumentException("缺少分页信息");
            }
            Sort sortById = new Sort(Sort.Direction.DESC, sortBy);
            Pageable pageable = new PageRequest(Integer.parseInt(activePage) - 1, Integer.parseInt(rowsOnPage));
            Page<SaleDetailVO> details=saleDetailLogic.getSaleDetails(pageable,startTime,endTime,goodName);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,details);
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.SALESTRATEGY_NOT_EXISTS);
        }
    }
}
