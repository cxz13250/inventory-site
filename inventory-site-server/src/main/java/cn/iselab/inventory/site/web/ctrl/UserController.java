package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ErrorResult;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.StatusCode;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.web.data.UserVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by ROGK on 2017/9/14.
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserLogic userLogic;

    @RequestMapping(value = UrlConstants.API_USER+"/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody @NotNull UserVO userVO, HttpServletRequest request){
        if(userVO.getEmail()==null&&userVO.getMobile()==null)
            return new ErrorResult(StatusCode.MISS_PARAMETER);
        try {
            UserVO userVO1=userLogic.login(userVO,request);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT, userVO1);
        }catch (Exception ex){
            return new ErrorResult(StatusCode.USER_NOT_EXISTS);
        }
    }

    @RequestMapping(value = UrlConstants.API_USER, method = RequestMethod.POST)
    public Map<String, Object> register(@RequestBody @NotNull UserVO userVO, HttpServletRequest request){
        try {
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT, userLogic.register(userVO,request));
        }catch (Exception ex){
            return new ErrorResult(ex.getMessage());
        }
    }
}
