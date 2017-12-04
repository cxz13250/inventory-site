package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Menu;
import cn.iselab.inventory.site.web.data.MenuVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:19 2017/12/4
 * @Modified By:
 */
@Service
public class MenuVOWrapper extends BaseWrapper<MenuVO,Menu>{

    @Override
    public MenuVO wrap(Menu menu){
        MenuVO vo=new MenuVO();
        vo.setDetail(menu.getDetail());
        vo.setMenu(menu.getMenu());
        vo.setUrl(menu.getUrl());
        return vo;
    }

    @Override
    public Menu unwrap(MenuVO vo){
        Menu menu=new Menu();
        menu.setDetail(vo.getDetail());
        menu.setMenu(vo.getMenu());
        menu.setUrl(vo.getUrl());
        return menu;
    }
}
