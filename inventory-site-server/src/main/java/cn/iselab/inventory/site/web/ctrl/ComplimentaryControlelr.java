package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.web.data.ComplimentaryVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.ComplimentaryLogic;
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
 * @Date: Created in 上午12:59 2017/12/8
 * @Modified By:
 */

@RestController
public class ComplimentaryControlelr extends BaseController {

    @Autowired
    ComplimentaryLogic complimentaryLogic;

    @RequestMapping(value = UrlConstants.API+"purchases",method = RequestMethod.GET)
    public Map<String,Object> getComplimentaries(@RequestParam(value = "keyword")String keyword,
                                             @RequestParam(value = "sortBy")String sortBy,
                                             HttpServletRequest request){
        String activePage = request.getHeader("activePage");
        String rowsOnPage = request.getHeader("rowsOnPage");
        if(activePage == null || rowsOnPage == null) {
            throw new IllegalArgumentException("缺少分页信息");
        }
        Sort sortById = new Sort(Sort.Direction.DESC, sortBy);
        Pageable pageable = new PageRequest(Integer.parseInt(activePage) - 1, Integer.parseInt(rowsOnPage),sortById);
        Page<ComplimentaryVO> orderVOS=complimentaryLogic.getComplimentaries(keyword,pageable);
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,orderVOS);
    }

    @RequestMapping(value = UrlConstants.API_COMPLIMENTARY,method = RequestMethod.GET)
    public Map<String,Object> getComplimentary(@RequestParam(name = "orderId")Long orderId){
        try {
            ComplimentaryVO orderVO=complimentaryLogic.getComplimentary(orderId);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,orderVO);
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.COMPLIMENTARY_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_COMPLIMENTARY,method = RequestMethod.POST)
    public Map<String,Object> createComplimentary(@RequestBody @NotNull ComplimentaryVO orderVO){
        Long id= complimentaryLogic.createComplimentary(orderVO);
        SuccessResult successResult = new SuccessResult();
        successResult.put(ResponseMessage.ID_RESULT, id);
        return successResult;
    }

    @RequestMapping(value = UrlConstants.API_COMPLIMENTARY,method = RequestMethod.PUT)
    public Map<String,Object> updateComplimentary(@RequestBody @NotNull ComplimentaryVO orderVO){
        try {
            complimentaryLogic.updateComplimentary(orderVO);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.COMPLIMENTARY_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_COMPLIMENTARY,method = RequestMethod.DELETE)
    public Map<String,Object> deleteComplimentary(@RequestParam(name = "orderId")Long orderId){
        try {
            complimentaryLogic.deleteComplimentary(orderId);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.COMPLIMENTARY_NOT_EXISTS);
        }
    }
}
