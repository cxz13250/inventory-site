package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.User2Role;
import cn.iselab.inventory.site.service.MenuService;
import cn.iselab.inventory.site.service.UserService;
import cn.iselab.inventory.site.web.data.MenuVO;
import cn.iselab.inventory.site.web.data.wrapper.MenuVOWrapper;
import cn.iselab.inventory.site.web.logic.MenuLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:23 2017/12/4
 * @Modified By:
 */
@Service
public class MenuLogicImpl implements MenuLogic {

    @Autowired
    MenuService menuService;

    @Autowired
    MenuVOWrapper menuVOWrapper;

    @Override
    public List<MenuVO> getMenusForLogin(List<Long> roles){
        List<MenuVO> menuVOS=new ArrayList<>();
        for(Long role:roles){
            List<MenuVO> menuVOList=menuVOWrapper.wrap(menuService.getMenusByRoleId(role));
            for (MenuVO m: menuVOList) {
                if (menuVOS.stream().anyMatch(menuVO -> menuVO.getDetail().equals(m.getDetail()))){
                    menuVOS.add(m);
                }
            }
        }
        return menuVOS;
    }

    @Override
    public List<MenuVO> getMenusForLogin2(Long roleId){
        List<MenuVO> menuVOS=new ArrayList<>();
        List<MenuVO> menuVOList=menuVOWrapper.wrap(menuService.getMenusByRoleId(roleId));
        for (MenuVO m: menuVOList) {
            if (!menuVOS.stream().anyMatch(menuVO -> menuVO.getUrl().equals(m.getUrl()))){
                menuVOS.add(m);
            }
        }
        return menuVOS;
    }
}
