package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Custom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:39 2017/11/6
 * @Modified By:
 */
public interface CustomService {

    long createCustom(Custom custom);

    Custom getCustom(long customId);

    Page<Custom> getCustoms(String keywrod, Pageable pageable);

    void updateCustom(Custom custom);

    void deletCustom(Custom custom);
}
