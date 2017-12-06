package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.web.data.ReceiptVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.ReceiptLogic;
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
 * @Date: Created in 下午11:48 2017/12/6
 * @Modified By:
 */
@RestController
public class ReceiptController extends BaseController{

    @Autowired
    ReceiptLogic receiptLogic;

    @RequestMapping(value = UrlConstants.API+"receipts",method = RequestMethod.GET)
    public Map<String,Object> getReceipts(@RequestParam(value = "keyword")String keyword,
                                          @RequestParam(value = "sortBy")String sortBy,
                                          HttpServletRequest request){
        String activePage = request.getHeader("activePage");
        String rowsOnPage = request.getHeader("rowsOnPage");
        if(activePage == null || rowsOnPage == null) {
            throw new IllegalArgumentException("缺少分页信息");
        }
        Sort sortById = new Sort(Sort.Direction.DESC, sortBy);
        Pageable pageable = new PageRequest(Integer.parseInt(activePage) - 1, Integer.parseInt(rowsOnPage),sortById);
        Page<ReceiptVO> orderVOS=receiptLogic.getReceipts(keyword,pageable);
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,orderVOS);
    }

    @RequestMapping(value = UrlConstants.API_RECEIPT,method = RequestMethod.GET)
    public Map<String,Object> getReceipt(@RequestParam(name = "number")String number){
        try {
            ReceiptVO orderVO=receiptLogic.getReceipt(number);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,orderVO);
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.Receipt_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_RECEIPT,method = RequestMethod.POST)
    public Map<String,Object> createReceipt(@RequestBody @NotNull ReceiptVO orderVO){
        String number=receiptLogic.createReceipt(orderVO);
        SuccessResult successResult = new SuccessResult();
        successResult.put(ResponseMessage.ID_RESULT, number);
        return successResult;
    }

    @RequestMapping(value = UrlConstants.API_RECEIPT,method = RequestMethod.PUT)
    public Map<String,Object> updateReceipt(@RequestBody @NotNull ReceiptVO orderVO){
        try {
            receiptLogic.updateReceipt(orderVO);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.Receipt_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_RECEIPT,method = RequestMethod.DELETE)
    public Map<String,Object> deleteReceipt(@RequestParam(name = "number")String number){
        try {
            receiptLogic.deleteReceipt(number);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.Receipt_NOT_EXISTS);
        }
    }
}
