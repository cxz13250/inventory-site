package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.model.UserOperation;
import cn.iselab.inventory.site.web.data.UserOperationVO;
import cn.iselab.inventory.site.web.data.UserVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.UserLogic;
import cn.iselab.inventory.site.web.logic.UserOperationLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * Created by ROGK on 2017/9/14.
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserLogic userLogic;

    @Autowired
    private UserOperationLogic operationLogic;

    @RequestMapping(value = UrlConstants.API_USER+"/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody @NotNull UserVO userVO, HttpServletRequest request){
        if(userVO.getEmail()==null&&userVO.getMobile()==null)
            return new ErrorResult(StatusCode.MISS_PARAMETER);
        try {
            UserVO userVO1=userLogic.login(userVO,request);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT, userVO1);
        }catch (HttpBadRequestException ex){
            return new ErrorResult(StatusCode.USER_NOT_EXISTS);
        }catch (IllegalArgumentException ex){
            return new ErrorResult(StatusCode.PASSWORD_INCORRECT);
        }
    }

    @RequestMapping(value = UrlConstants.API_USER+"/register", method = RequestMethod.POST)
    public Map<String, Object> register(@RequestBody @NotNull UserVO userVO, HttpServletRequest request){
        try {
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT, userLogic.register(userVO,request));
        }catch (Exception ex){
            return new ErrorResult(ex.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API+"users",method = RequestMethod.GET)
    public Map<String,Object> getList(@RequestParam(value = "keyword",required = false)String keyword, HttpServletRequest request){
        try{
            List<UserVO> userVOS=userLogic.getList(request,keyword);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,userVOS);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_USER,method = RequestMethod.GET)
    public Map<String,Object> getUser(@RequestParam(value = "userId") Long userId,HttpServletRequest request){
        try{
            UserVO userVO=userLogic.getUser(userId,request);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,userVO);
        }catch (Exception e){
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_USER,method = RequestMethod.POST)
    public Map<String,Object> updateUser(@RequestBody @NotNull UserVO vo,HttpServletRequest request){
        try{
            UserVO userVO=userLogic.updateUser(vo,request);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT,userVO);
        }catch (Exception e){
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_OPERATION,method = RequestMethod.GET)
    public Map<String,Object> getOperations(@RequestParam(value = "keyword",required = false) String keyword,
                                      @RequestParam(value = "sortBy")String sortBy,HttpServletRequest request){
        String activePage = request.getHeader("activePage");
        String rowsOnPage = request.getHeader("rowsOnPage");
        if(activePage == null || rowsOnPage == null) {
            throw new IllegalArgumentException("缺少分页信息");
        }
        Sort sortById = new Sort(Sort.Direction.DESC, sortBy);
        Pageable pageable = new PageRequest(Integer.parseInt(activePage) - 1, Integer.parseInt(rowsOnPage),sortById);
        Page<UserOperationVO> operations=operationLogic.getOperationList(pageable,keyword);
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,operations);
    }

    @RequestMapping(value = UrlConstants.API_USER,method = RequestMethod.DELETE)
    public Map<String,Object> deleteUser(@RequestParam(value = "userId")Long userId,HttpServletRequest request){
        try{
            userLogic.deleteUser(userId,request);
            return SuccessResult.ok();
        }catch (Exception e){
            return new ErrorResult(e.getMessage());
        }
    }
}
