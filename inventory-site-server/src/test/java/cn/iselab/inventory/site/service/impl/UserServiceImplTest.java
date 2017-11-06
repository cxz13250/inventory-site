package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.User2RoleDao;
import cn.iselab.inventory.site.dao.UserDao;
import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.model.User2Role;
import cn.iselab.inventory.site.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:09 2017/11/6
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserService userService=new UserServiceImpl();

    @Mock
    private UserDao userDao;

    @Mock
    private User2RoleDao user2RoleDao;

    User user=new User();
    User2Role user2Role=new User2Role();
    List<User2Role> user2Roles=new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        user.setId(1L);
        user.setName("test");
        user.setEmail("test");
        user.setMobile("test");

        user2Role.setId(1L);
        user2Role.setUserId(1L);
        user2Role.setRoleId(1L);

        user2Roles.add(user2Role);
    }

    @Test
    public void should_createUser_when_givenUser() throws Exception {
        userService.createUser(user);

        Mockito.verify(userDao).save(any(User.class));
    }

    @Test
    public void should_returnUser_when_givenEmail() throws Exception {
        when(userDao.findByEmail(anyString())).thenReturn(user);

        User result=userService.getUserByEmail("test");

        Assert.assertEquals(user.getId(),result.getId());
        Assert.assertEquals(user.getName(),result.getName());
        Assert.assertEquals(user.getMobile(),result.getMobile());
    }

    @Test
    public void should_returnUser_when_givenMobile() throws Exception {
        when(userDao.findBymobile(anyString())).thenReturn(user);

        User result=userService.getUserByMobile("test");

        Assert.assertEquals(user.getId(),result.getId());
        Assert.assertEquals(user.getName(),result.getName());
        Assert.assertEquals(user.getMobile(),result.getMobile());
    }

    @Test
    public void should_createUser2Role_when_givenUser2Role() throws Exception{
        userService.createRole(user2Role);

        Mockito.verify(user2RoleDao).save(any(User2Role.class));
    }

    @Test
    public void should_returnRoles_when_givenUserId() throws Exception{
        when(user2RoleDao.findByUserId(anyLong())).thenReturn(user2Roles);

        List<User2Role> result=userService.getRoles(1L);

        Assert.assertEquals(result.get(0).getId(),user2Roles.get(0).getId());
    }

    @Test
    public void should_updateUser_when_givenUser() throws Exception{
        userService.updateUser(user);

        Mockito.verify(userDao).save(any(User.class));
    }
}