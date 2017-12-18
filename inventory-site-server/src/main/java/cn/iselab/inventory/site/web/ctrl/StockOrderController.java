package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.web.data.StockOrderVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.StockOrderLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:16 2017/12/8
 * @Modified By:
 */
@RestController
public class StockOrderController extends BaseController{

    @Autowired
    StockOrderLogic stockOrderLogic;

    @RequestMapping(value = UrlConstants.API+"stockOrders",method = RequestMethod.GET)
    public Map<String,Object> getStockOrders(@RequestParam(value = "keyword")String keyword,
                                            @RequestParam(value = "sortBy")String sortBy,
                                            @RequestParam(value = "type")Long type,
                                            HttpServletRequest request){
        String activePage = request.getHeader("activePage");
        String rowsOnPage = request.getHeader("rowsOnPage");
        if(activePage == null || rowsOnPage == null) {
            throw new IllegalArgumentException("缺少分页信息");
        }
        Sort sortById = new Sort(Sort.Direction.DESC, sortBy);
        Pageable pageable = new PageRequest(Integer.parseInt(activePage) - 1, Integer.parseInt(rowsOnPage),sortById);
        Page<StockOrderVO> orderVOS=stockOrderLogic.getStockOrders(keyword,pageable,type);
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,orderVOS);
    }

    @RequestMapping(value = UrlConstants.API_STOCKORDER,method = RequestMethod.GET)
    public Map<String,Object> getStockOrder(@RequestParam(name = "orderId")Long orderId){
        try {
            StockOrderVO orderVO=stockOrderLogic.getStockOrder(orderId);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,orderVO);
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.STOCKORDER_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_STOCKORDER,method = RequestMethod.POST)
    public Map<String,Object> createStockOrder(@RequestBody @NotNull StockOrderVO orderVO){
        Long id= stockOrderLogic.createStockOrder(orderVO);
        SuccessResult successResult = new SuccessResult();
        successResult.put(ResponseMessage.ID_RESULT, id);
        return successResult;
    }

    @RequestMapping(value = UrlConstants.API_STOCKORDER,method = RequestMethod.PUT)
    public Map<String,Object> updateStockOrder(@RequestBody @NotNull StockOrderVO orderVO){
        try {
            stockOrderLogic.updateStockOrder(orderVO);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.STOCKORDER_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_STOCKORDER,method = RequestMethod.DELETE)
    public Map<String,Object> deleteStockOrder(@RequestParam(name = "orderId")Long orderId){
        try {
            stockOrderLogic.deleteStockOrder(orderId);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.STOCKORDER_NOT_EXISTS);
        }
    }
}
