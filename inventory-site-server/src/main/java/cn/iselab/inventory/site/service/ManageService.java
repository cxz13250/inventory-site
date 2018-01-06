package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.ManageInfo;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:49 2018/1/6
 * @Modified By:
 */
public interface ManageService {

    ManageInfo getManageInfo(long id);

    List<ManageInfo> getManageInfos();
}
