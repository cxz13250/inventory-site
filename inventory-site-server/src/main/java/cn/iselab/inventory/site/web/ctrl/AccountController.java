package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.web.data.AccountVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.AccountLogic;
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
 * @Date: Created in 下午11:00 2017/12/4
 * @Modified By:
 */

@RestController
public class AccountController extends BaseController{

    @Autowired
    AccountLogic accountLogic;

    @RequestMapping(value = UrlConstants.API+"accounts",method = RequestMethod.GET)
    public Map<String,Object> getAccounts(@RequestParam(value = "keyword")String keyword,
                                         @RequestParam(value = "sortBy")String sortBy,
                                         HttpServletRequest request){
        String activePage = request.getHeader("activePage");
        String rowsOnPage = request.getHeader("rowsOnPage");
        if(activePage == null || rowsOnPage == null) {
            throw new IllegalArgumentException("缺少分页信息");
        }
        Sort sortById = new Sort(Sort.Direction.DESC, sortBy);
        Pageable pageable = new PageRequest(Integer.parseInt(activePage) - 1, Integer.parseInt(rowsOnPage),sortById);
        Page<AccountVO> accounts=accountLogic.getAccounts(keyword,pageable);
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,accounts);
    }

    @RequestMapping(value = UrlConstants.API_ACCOUNT,method = RequestMethod.GET)
    public Map<String,Object> getAccount(@RequestParam(name = "accountId")Long accountId){
        try {
            AccountVO accountVO=accountLogic.getAccount(accountId);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,accountVO);
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.ACCOUNT_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_ACCOUNT,method = RequestMethod.POST)
    public Map<String,Object> createAccount(@RequestBody @NotNull AccountVO accountVO){
        Long id = accountLogic.createAccount(accountVO);
        SuccessResult successResult = new SuccessResult();
        successResult.put(ResponseMessage.ID_RESULT, id);
        return successResult;
    }

    @RequestMapping(value = UrlConstants.API_ACCOUNT,method = RequestMethod.PUT)
    public Map<String,Object> updateAccount(@RequestBody @NotNull AccountVO accountVO){
        try {
            accountLogic.updateAccount(accountVO);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.ACCOUNT_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_ACCOUNT,method = RequestMethod.DELETE)
    public Map<String,Object> deleteAccount(@RequestParam(name = "accountId")Long accountId){
        try {
            accountLogic.deleteAccount(accountId);
            return SuccessResult.ok();
        }catch (HttpBadRequestException e){
            return new ErrorResult(StatusCode.ACCOUNT_NOT_EXISTS);
        }
    }
}
