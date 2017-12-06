package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.web.data.PaymentVO;
import cn.iselab.inventory.site.web.data.wrapper.PaymentVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.PaymentLogic;
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
 * @Date: Created in 下午9:58 2017/12/6
 * @Modified By:
 */
@RestController
public class PaymentController extends BaseController{

    @Autowired
    PaymentLogic paymentLogic;

    @RequestMapping(value = UrlConstants.API+"payments",method = RequestMethod.GET)
    public Map<String,Object> getPayments(@RequestParam(value = "keyword")String keyword,
                                            @RequestParam(value = "sortBy")String sortBy,
                                            HttpServletRequest request){
        String activePage = request.getHeader("activePage");
        String rowsOnPage = request.getHeader("rowsOnPage");
        if(activePage == null || rowsOnPage == null) {
            throw new IllegalArgumentException("缺少分页信息");
        }
        Sort sortById = new Sort(Sort.Direction.DESC, sortBy);
        Pageable pageable = new PageRequest(Integer.parseInt(activePage) - 1, Integer.parseInt(rowsOnPage),sortById);
        Page<PaymentVO> orderVOS=paymentLogic.getPayments(keyword,pageable);
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,orderVOS);
    }

    @RequestMapping(value = UrlConstants.API_PAYMENT,method = RequestMethod.GET)
    public Map<String,Object> getPayment(@RequestParam(name = "number")String number){
        try {
            PaymentVO orderVO=paymentLogic.getPayment(number);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,orderVO);
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.Payment_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_PAYMENT,method = RequestMethod.POST)
    public Map<String,Object> createPayment(@RequestBody @NotNull PaymentVO orderVO){
        String number=paymentLogic.createPayment(orderVO);
        SuccessResult successResult = new SuccessResult();
        successResult.put(ResponseMessage.ID_RESULT, number);
        return successResult;
    }

    @RequestMapping(value = UrlConstants.API_PAYMENT,method = RequestMethod.PUT)
    public Map<String,Object> updatePayment(@RequestBody @NotNull PaymentVO orderVO){
        try {
            paymentLogic.updatePayment(orderVO);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.Payment_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_PAYMENT,method = RequestMethod.DELETE)
    public Map<String,Object> deletePayment(@RequestParam(name = "number")String number){
        try {
            paymentLogic.deletePayment(number);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.Payment_NOT_EXISTS);
        }
    }
}
