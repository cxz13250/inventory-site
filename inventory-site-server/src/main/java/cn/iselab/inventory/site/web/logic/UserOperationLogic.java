package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.UserOperation;
import cn.iselab.inventory.site.web.data.UserOperationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午4:33 2017/12/5
 * @Modified By:
 */
public interface UserOperationLogic {

    void recordUserOperation(HttpServletRequest request,Long userId,String operation);

    Page<UserOperationVO> getOperationList(Pageable pageable, String keyword);
}
