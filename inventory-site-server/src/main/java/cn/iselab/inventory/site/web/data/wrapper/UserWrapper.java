package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.util.EncryptionUtil;
import cn.iselab.inventory.site.web.data.UserVO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by ROGK on 2017/9/14.
 */
@Service
public class UserWrapper extends BaseWrapper<UserVO,User> {

    @Override
    public UserVO wrap(User user){
        UserVO userVO=new UserVO();
        userVO.setId(user.getId());
        userVO.setCreateTime(user.getCreateTime().getTime());
        userVO.setEmail(user.getEmail());
        userVO.setMobile(user.getMobile());
        userVO.setName(user.getName());
        userVO.setPassword(user.getPassword());
        return userVO;
    }

    @Override
    public User unwrap(UserVO userVO){
        User user=new User();
        user.setEmail(userVO.getEmail());
        user.setMobile(userVO.getMobile());
        user.setPassword(EncryptionUtil.encryptMD5(userVO.getPassword()));
        user.setName(userVO.getName());
        return user;
    }
}
