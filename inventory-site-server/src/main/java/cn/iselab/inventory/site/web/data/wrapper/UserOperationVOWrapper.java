package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.UserOperation;
import cn.iselab.inventory.site.web.data.UserOperationVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:51 2017/12/17
 * @Modified By:
 */
@Service
public class UserOperationVOWrapper extends BaseWrapper<UserOperationVO,UserOperation> {

    @Override
    public UserOperationVO wrap(UserOperation operation){
        UserOperationVO vo=new UserOperationVO();
        vo.setCreateTime(operation.getCreateTime().getTime());
        vo.setIp(operation.getIp());
        vo.setOperation(operation.getOperation());
        return vo;
    }

    @Override
    public UserOperation unwrap(UserOperationVO vo){
        UserOperation operation=new UserOperation();
        operation.setIp(vo.getIp());
        operation.setOperation(vo.getOperation());
        return operation;
    }
}
