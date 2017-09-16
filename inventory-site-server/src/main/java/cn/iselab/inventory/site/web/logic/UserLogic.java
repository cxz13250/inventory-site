package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.web.data.UserVO;

/**
 * Created by ROGK on 2017/9/14.
 */
public interface UserLogic {

    UserVO getUserByEmailOrMobile(UserVO userVO) throws Exception;

    UserVO register(UserVO userVO) throws Exception;
}
