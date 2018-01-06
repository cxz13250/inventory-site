package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.common.constanst.UrlConstants;
import cn.iselab.inventory.site.common.web.ResponseMessage;
import cn.iselab.inventory.site.common.web.SuccessResult;
import cn.iselab.inventory.site.web.logic.ManageLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:48 2018/1/6
 * @Modified By:
 */
@RestController
public class ManageController extends BaseController{

    @Autowired
    ManageLogic manageLogic;

    @RequestMapping(value = UrlConstants.API_MANAGE+"/info",method = RequestMethod.GET)
    public Map<String,Object> getManageInfo(@RequestParam(value = "startTime")Long startTime,
                                            @RequestParam(value = "endTime")Long endTime) throws Exception{
        return SuccessResult.ok(ResponseMessage.ITEM_RESULT,manageLogic.getManageInfo(startTime,endTime));
    }
}
