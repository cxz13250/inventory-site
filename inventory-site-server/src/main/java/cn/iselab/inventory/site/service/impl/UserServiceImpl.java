package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.dao.RoleDao;
import cn.iselab.inventory.site.dao.User2RoleDao;
import cn.iselab.inventory.site.dao.UserDao;
import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.model.Role;
import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.model.User2Role;
import cn.iselab.inventory.site.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ROGK on 2017/9/14.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private User2RoleDao user2RoleDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public User createUser(User user){
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setDelete(DeleteStatus.IS_NOT_DELETE);
        return userDao.save(user);
    }

    @Override
    public User getUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    @Override
    public User getUserByMobile(String mobile){
        return userDao.findBymobile(mobile);
    }

    @Override
    public List<User> getUsers(String keyword){
        Specifications<User> where=Specifications.where(getWhereClause(keyword));
        return userDao.findAll(where);
    }

    @Override
    public User2Role createRole(User2Role user2Role){
        return user2RoleDao.save(user2Role);
    }

    @Override
    public List<User2Role> getRoles(long userId){
        return user2RoleDao.findByUserId(userId);
    }

    @Override
    public void updateUser(User user){
        userDao.save(user);
    }

    @Override
    public Role findRole(long id){
        return roleDao.findOne(id);
    }

    @Override
    public User getUser(long id){
        return userDao.findOne(id);
    }

    @Override
    public void deleteUser(User user){
        user.setDelete(DeleteStatus.IS_DELETE);
        userDao.save(user);
    }

    private Specification<User> getWhereClause(String keyword){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                predicate.getExpressions().add(
                        criteriaBuilder.equal(root.get("deleted"), DeleteStatus.IS_NOT_DELETE));
                if (keyword != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.like(root.get("name"), "%"+StringUtils.trim(keyword)+"%")
                    );
                }
                return predicate;
            }
        };
    }
}
