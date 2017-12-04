package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.model.User2Role;
import cn.iselab.inventory.site.service.UserService;
import cn.iselab.inventory.site.util.EncryptionUtil;
import cn.iselab.inventory.site.web.data.UserVO;
import cn.iselab.inventory.site.web.data.wrapper.UserWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.MenuLogic;
import cn.iselab.inventory.site.web.logic.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    @Override
    public UserVO login(UserVO userVO) throws Exception{
       User user;
       if(userVO.getEmail()!=null)
           user=userService.getUserByEmail(userVO.getEmail());
       else
           user=userService.getUserByMobile(userVO.getMobile());
       if(user==null)
           throw new HttpBadRequestException("user not exist");
       else if(user.getPassword()!= EncryptionUtil.encryptMD5(userVO.getPassword())){
           throw new HttpBadRequestException("password is not right");
       }
       UserVO vo=userWrapper.wrap(user);
       List<User2Role> roles=userService.getRoles(user.getId());

       //设置菜单
       vo.setMenus(menuLogic.getMenusForLogin(roles.stream().map(User2Role::toLong).collect(Collectors.toList())));

       //设置角色
       vo.setRoles(roles.stream().map(User2Role::toLong).collect(Collectors.toList()));
       return vo;
    }

    @Override
    public UserVO register(UserVO userVO) throws Exception{
        if (userVO.getPassword().length() > 16 || userVO.getPassword().length() < 6) {
            throw new HttpBadRequestException("the length of password should between 6 and 16");
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
        List<Long> roles=userVO.getRoles();
        roles.forEach(role-> {
            User2Role user2Role=new User2Role();
            user2Role.setRoleId(role);
            user2Role.setUserId(userVO.getId());
            user2Role.setCreateTime(new Timestamp(System.currentTimeMillis()));
            userService.createRole(user2Role);
        });
        UserVO vo = userWrapper.wrap(user);

        //设置菜单
        vo.setMenus(menuLogic.getMenusForLogin(roles));
        return vo;
    }
}
