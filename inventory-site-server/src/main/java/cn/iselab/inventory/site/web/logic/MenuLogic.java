package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.User2Role;
import cn.iselab.inventory.site.web.data.MenuVO;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:23 2017/12/4
 * @Modified By:
 */
public interface MenuLogic {

    List<MenuVO> getMenusForLogin(List<Long> roleId);
}
