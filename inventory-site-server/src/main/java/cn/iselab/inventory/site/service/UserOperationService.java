package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.UserOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:40 2017/11/6
 * @Modified By:
 */
public interface UserOperationService {

    void create(UserOperation userOperation);

    Page<UserOperation> getUserOperations(String keyword, Pageable pageable);

    void delete(UserOperation userOperation);
}
