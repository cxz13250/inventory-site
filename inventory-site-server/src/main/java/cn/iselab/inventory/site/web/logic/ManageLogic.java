package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.ManageInfo;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:49 2018/1/6
 * @Modified By:
 */
public interface ManageLogic {

    ManageInfo getManageInfo(long startTime,long endTime);
}
