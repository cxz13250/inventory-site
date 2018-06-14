package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.common.constanst.OperationStatus;
import cn.iselab.inventory.site.model.Role;
import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.model.User2Role;
import cn.iselab.inventory.site.service.UserService;
import cn.iselab.inventory.site.util.EncryptionUtil;
import cn.iselab.inventory.site.web.data.UserVO;
import cn.iselab.inventory.site.web.data.wrapper.UserWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.MenuLogic;
import cn.iselab.inventory.site.web.logic.UserLogic;
import cn.iselab.inventory.site.web.logic.UserOperationLogic;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ROGK on 2017/9/14.
 */
@Service
public class UserLogicImpl implements UserLogic{

    @Autowired
    private UserService userService;

    @Autowired
    private UserWrapper userWrapper;

    @Autowired
    private MenuLogic menuLogic;

    @Autowired
    private UserOperationLogic userOperationLogic;

    @Override
    public UserVO login(UserVO userVO, HttpServletRequest request){
        UsernamePasswordToken token=new UsernamePasswordToken(
                userVO.getEmail()!=null?userVO.getEmail():userVO.getMobile(),
                EncryptionUtil.encryptMD5(userVO.getPassword()));
        Subject subject= SecurityUtils.getSubject();
        subject.login(token);
        if (subject.isAuthenticated()) {
            User user;
            if (userVO.getEmail() != null)
                user = userService.getUserByEmail(userVO.getEmail());
            else
                user = userService.getUserByMobile(userVO.getMobile());
            if (user == null || user.isDelete() == DeleteStatus.IS_DELETE)
                throw new HttpBadRequestException("用户不存在");
            else if (!user.getPassword().equals(EncryptionUtil.encryptMD5(userVO.getPassword()))) {
                throw new IllegalArgumentException("密码错误");
            }
            UserVO vo = userWrapper.wrap(user);
            vo.setPassword("");
            User2Role role = userService.getRoles(user.getId()).get(0);

            //设置菜单
            vo.setMenus(menuLogic.getMenusForLogin2(role.getRoleId()));

            //设置角色
            vo.setRoleId(role.getRoleId());
            vo.setRoleName(userService.findRole(role.getRoleId()).getName());

            userOperationLogic.recordUserOperation(request, user.getId(), OperationStatus.LOGIN);

            SecurityUtils.getSubject().getSession().setAttribute("user", vo);
            return vo;
        }else {
            token.clear();
            throw new UnauthorizedException("login failed");
        }
    }

    @Override
    public UserVO register(UserVO userVO,HttpServletRequest request) throws Exception{
        if (userVO.getPassword().length() > 15 || userVO.getPassword().length() < 5) {
            throw new HttpBadRequestException("the length of password should between 5 and 15");
        }
        if(userService.getUserByEmail(userVO.getEmail())!=null){
            throw new HttpBadRequestException("user already exist");
        }
        if(userService.getUserByMobile(userVO.getMobile())!=null){
            throw new HttpBadRequestException("user already exist");
        }
        User user=userWrapper.unwrap(userVO);
        user=userService.createUser(user);

        //设置角色
        Long roleId=userVO.getRoleId();
        User2Role user2Role=new User2Role();
        user2Role.setRoleId(roleId);
        user2Role.setUserId(user.getId());
        user2Role.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userService.createRole(user2Role);
        UserVO vo = userWrapper.wrap(user);

        //设置菜单
        vo.setMenus(menuLogic.getMenusForLogin2(roleId));

        //设置角色
        vo.setRoleId(roleId);
        vo.setRoleName(userService.findRole(roleId).getName());

        HttpSession session=request.getSession();
        Long userId=(Long) session.getAttribute("id");

        userOperationLogic.recordUserOperation(request,userId, OperationStatus.REGISTER);
        return vo;
    }

    @Override
    public List<UserVO> getList(HttpServletRequest request,String keyword) throws Exception{
        List<User> users=userService.getUsers(keyword);
        List<UserVO> userVOS=new ArrayList<>();
        for (User user:users){
            UserVO userVO=userWrapper.wrap(user);
            User2Role role=userService.getRoles(user.getId()).get(0);
            userVO.setRoleId(role.getRoleId());
            userVO.setRoleName(userService.findRole(role.getRoleId()).getName());
            userVOS.add(userVO);
        }

        HttpSession session=request.getSession();
        Long userId=(Long) session.getAttribute("id");
        userOperationLogic.recordUserOperation(request,userId, OperationStatus.USER_LIST);
        return userVOS;
    }

    @Override
    public UserVO getUser(Long userId, HttpServletRequest request){
        User user=userService.getUser(userId);
        if (user == null) {
            throw new HttpBadRequestException("用户不存在");
        }
        UserVO userVO=userWrapper.wrap(user);
        User2Role role=userService.getRoles(user.getId()).get(0);
        userVO.setRoleId(role.getRoleId());
        userVO.setRoleName(userService.findRole(role.getRoleId()).getName());
        userVO.setPassword("");
        return userVO;
    }

    @Override
    public UserVO updateUser(UserVO vo, HttpServletRequest request){
        User user=userService.getUser(vo.getId());
        if (user == null) {
            throw new HttpBadRequestException("用户不存在");
        }
        updateInfo(user,vo);
        userService.updateUser(user);
        userOperationLogic.recordUserOperation(request,0L, OperationStatus.REGISTER);
        return vo;
    }

    @Override
    public void deleteUser(Long userId, HttpServletRequest request){
        User user=userService.getUser(userId);
        if (user == null) {
            throw new HttpBadRequestException("用户不存在");
        }
        userService.deleteUser(user);
    }

    private void updateInfo(User user,UserVO vo){
        if(!vo.getPassword().equals("")){
            user.setPassword(EncryptionUtil.encryptMD5(vo.getPassword()));
        }
        if(!vo.getEmail().equals("")){
            user.setEmail(vo.getEmail());
        }
        if(!vo.getName().equals("")){
            user.setName(vo.getName());
        }
        if(!vo.getMobile().equals("")){
            user.setMobile(vo.getMobile());
        }
        User2Role role=userService.getRoles(user.getId()).get(0);
        if(vo.getRoleId()!=role.getRoleId()){
            role.setRoleId(vo.getRoleId());
            userService.updateRole(role);
        }
    }
}
