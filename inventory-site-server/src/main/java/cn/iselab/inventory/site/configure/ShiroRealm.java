package cn.iselab.inventory.site.configure;

import cn.iselab.inventory.site.model.Role;
import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.service.UserService;
import cn.iselab.inventory.site.util.EncryptionUtil;
import cn.iselab.inventory.site.web.data.UserVO;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:42 2018/6/14
 * @Modified By:
 */
public class ShiroRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserVO user  = (UserVO) principals.getPrimaryPrincipal();
        Set<String> roles=new HashSet<>();
        roles.add(user.getRoleName());
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        //获取用户的输入的账号.
        UsernamePasswordToken userToken=(UsernamePasswordToken) token;
        String username=userToken.getUsername();
        String password=String.valueOf(userToken.getPassword());

        //通过username从数据库中查找 User对象，如果找到，没找到.
        User user = userService.getUserByEmail(username);
        if(user == null){
           user=userService.getUserByMobile(username);
        }
        if (user==null){
            throw new HttpBadRequestException("user not exists");
        }else {
            if (!password.equals(user.getPassword())){
                throw new IllegalArgumentException("wrong password");
            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), getName()
            );
            return authenticationInfo;
        }
    }
}
