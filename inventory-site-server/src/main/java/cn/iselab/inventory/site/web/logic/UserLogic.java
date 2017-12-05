package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.web.data.UserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ROGK on 2017/9/14.
 */
public interface UserLogic {

    UserVO login(UserVO userVO, HttpServletRequest request) throws Exception;

    UserVO register(UserVO userVO, HttpServletRequest request) throws Exception;
}
