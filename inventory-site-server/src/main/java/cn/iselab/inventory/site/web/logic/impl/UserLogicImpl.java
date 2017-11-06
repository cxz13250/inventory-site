package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.UserService;
import cn.iselab.inventory.site.util.EncryptionUtil;
import cn.iselab.inventory.site.web.data.UserVO;
import cn.iselab.inventory.site.web.data.wrapper.UserWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ROGK on 2017/9/14.
 */
@Service
public class UserLogicImpl implements UserLogic{

    @Autowired
    private UserService userService;

    @Autowired
    private UserWrapper userWrapper;

    public UserVO getUserByEmailOrMobile(UserVO userVO) throws Exception{
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
        return userWrapper.wrap(user);
    }

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
        return userWrapper.wrap(userService.createUser(user));
    }
}
