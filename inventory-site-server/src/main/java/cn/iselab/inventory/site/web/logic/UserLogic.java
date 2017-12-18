package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.web.data.UserVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ROGK on 2017/9/14.
 */
public interface UserLogic {

    UserVO login(UserVO userVO, HttpServletRequest request);

    UserVO register(UserVO userVO, HttpServletRequest request) throws Exception;

    List<UserVO> getList(HttpServletRequest request,String keyword) throws Exception;

    UserVO getUser(Long userId, HttpServletRequest request);

    UserVO updateUser(UserVO vo, HttpServletRequest request);

    void deleteUser(Long userId, HttpServletRequest request);
}
