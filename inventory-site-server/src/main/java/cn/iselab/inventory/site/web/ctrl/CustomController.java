package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.web.data.CustomVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.CustomLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:36 2017/12/4
 * @Modified By:
 */

@RestController
public class CustomController {

    @Autowired
    CustomLogic customLogic;

    @RequestMapping(value = UrlConstants.API+"customs",method = RequestMethod.GET)
    public Map<String,Object> getCustoms(@RequestParam(value = "keyword",required = false)String keyword,
                                         @RequestParam(value = "sortBy")String sortBy,
                                         HttpServletRequest request){
        String activePage = request.getHeader("activePage");
        String rowsOnPage = request.getHeader("rowsOnPage");
        if(activePage == null || rowsOnPage == null) {
            throw new IllegalArgumentException("缺少分页信息");
        }
        Sort sortById = new Sort(Sort.Direction.DESC, sortBy);
        Pageable pageable = new PageRequest(Integer.parseInt(activePage) - 1, Integer.parseInt(rowsOnPage),sortById);
        Page<CustomVO> customVOS=customLogic.getCustoms(keyword,pageable);
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,customVOS);
    }

    @RequestMapping(value = UrlConstants.API_CUSTOM,method = RequestMethod.GET)
    public Map<String,Object> getCustom(@RequestParam(name = "customId")Long customId){
        try {
            CustomVO customVO=customLogic.getCustom(customId);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,customVO);
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.CUSTOM_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_CUSTOM,method = RequestMethod.POST)
    public Map<String,Object> createCustom(@RequestBody @NotNull CustomVO customVO){
        Long id = customLogic.createCustom(customVO);
        SuccessResult successResult = new SuccessResult();
        successResult.put(ResponseMessage.ID_RESULT, id);
        return successResult;
    }

    @RequestMapping(value = UrlConstants.API_CUSTOM,method = RequestMethod.PUT)
    public Map<String,Object> updateCustom(@RequestBody @NotNull CustomVO customVO){
        try {
            customLogic.updateCustom(customVO);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.CUSTOM_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_CUSTOM,method = RequestMethod.DELETE)
    public Map<String,Object> deleteCustom(@RequestParam(name = "customId")Long customId){
        try {
            customLogic.deleteCustom(customId);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.CUSTOM_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API+"customs/receipt",method = RequestMethod.GET)
    public Map<String,Object> getCustomsForReceipt(HttpServletRequest request){
        List<CustomVO> vos=customLogic.getCustomsForReceipt();
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,vos);
    }

    @RequestMapping(value = UrlConstants.API+"customs/sale",method = RequestMethod.GET)
    public Map<String,Object> getCustomsForSale(HttpServletRequest request){
        List<CustomVO> vos=customLogic.getCustomsForSale();
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,vos);
    }

    @RequestMapping(value = UrlConstants.API+"customs/purchase",method = RequestMethod.GET)
    public Map<String,Object> getCustomsForPurchase(HttpServletRequest request){
        List<CustomVO> vos=customLogic.getCustomsForPurchase();
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,vos);
    }
}
