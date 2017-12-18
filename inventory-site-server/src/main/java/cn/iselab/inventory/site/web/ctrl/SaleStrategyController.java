package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.web.data.SaleStrategyVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.SaleStrategyLogic;
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
 * @Date: Created in 上午12:24 2017/12/8
 * @Modified By:
 */
@RestController
public class SaleStrategyController extends BaseController {

    @Autowired
    SaleStrategyLogic saleStrategyLogic;

    @RequestMapping(value = UrlConstants.API+"strategies",method = RequestMethod.GET)
    public Map<String,Object> getSaleOrders(@RequestParam(value = "keyword",required = false)String keyword,
                                            @RequestParam(value = "sortBy")String sortBy,
                                            HttpServletRequest request){
        String activePage = request.getHeader("activePage");
        String rowsOnPage = request.getHeader("rowsOnPage");
        if(activePage == null || rowsOnPage == null) {
            throw new IllegalArgumentException("缺少分页信息");
        }
        Sort sortById = new Sort(Sort.Direction.DESC, sortBy);
        Pageable pageable = new PageRequest(Integer.parseInt(activePage) - 1, Integer.parseInt(rowsOnPage),sortById);
        Page<SaleStrategyVO> orderVOS=saleStrategyLogic.getSaleStrategies(keyword,pageable);
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,orderVOS);
    }

    @RequestMapping(value = UrlConstants.API_STRATEGY,method = RequestMethod.GET)
    public Map<String,Object> getSaleOrder(@RequestParam(name = "id")Long id){
        try {
            SaleStrategyVO orderVO=saleStrategyLogic.getSaleStrategy(id);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,orderVO);
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.SALESTRATEGY_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_STRATEGY,method = RequestMethod.POST)
    public Map<String,Object> createPurchase(@RequestBody @NotNull SaleStrategyVO orderVO){
        Long id= saleStrategyLogic.createSaleStrategy(orderVO);
        SuccessResult successResult = new SuccessResult();
        successResult.put(ResponseMessage.ID_RESULT, id);
        return successResult;
    }

    @RequestMapping(value = UrlConstants.API_STRATEGY,method = RequestMethod.PUT)
    public Map<String,Object> updatePurchase(@RequestBody @NotNull SaleStrategyVO orderVO){
        try {
            saleStrategyLogic.updateSaleStrategy(orderVO);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.SALESTRATEGY_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_STRATEGY,method = RequestMethod.DELETE)
    public Map<String,Object> deletePurchase(@RequestParam(name = "id")Long id){
        try {
            saleStrategyLogic.deleteSaleStrategy(id);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.SALESTRATEGY_NOT_EXISTS);
        }
    }
}
