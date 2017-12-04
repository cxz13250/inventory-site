package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Menu;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:14 2017/12/4
 * @Modified By:
 */
public interface MenuService {

    List<Menu> getMenusByRoleId(long roleId);
}
