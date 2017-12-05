package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.UserOperation;
import cn.iselab.inventory.site.service.UserOperationService;
import cn.iselab.inventory.site.web.logic.BaseLogic;
import cn.iselab.inventory.site.web.logic.UserOperationLogic;
import cn.iselab.inventory.site.web.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午4:33 2017/12/5
 * @Modified By:
 */
@Service
public class UserOperationLogicImpl extends BaseLogic implements UserOperationLogic {

    @Autowired
    UserOperationService userOperationService;

    @Override
    public void recordUserOperation(HttpServletRequest request,Long userId,String operation){
        String ip = HttpUtil.getIpByRequest(request);
        if("101.37.78.167".equals(ip)) {
            return;
        }
        UserOperation userOperation = new UserOperation();
        userOperation.setIp(ip);
        userOperation.setUserId(userId);
        userOperation.setOperation(operation);
        Timestamp current=new Timestamp(System.currentTimeMillis());
        userOperation.setCreateTime(current);
        userOperationService.create(userOperation);
        LOG.info(String.format("User[%d] Login at [%s]",userId,current.toString()));
    }
}
