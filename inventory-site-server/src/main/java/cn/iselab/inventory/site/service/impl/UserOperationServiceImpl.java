package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.UserOperationDao;
import cn.iselab.inventory.site.model.UserOperation;
import cn.iselab.inventory.site.service.UserOperationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:40 2017/11/6
 * @Modified By:
 */
@Service
public class UserOperationServiceImpl implements UserOperationService{

    @Autowired
    UserOperationDao userOperationDao;

    @Override
    public void create(UserOperation userOperation){
        userOperationDao.save(userOperation);
    }

    @Override
    public Page<UserOperation> getUserOperations(String keyword, Pageable pageable){
        Specifications<UserOperation> where=Specifications.where(getWhereClause(keyword));
        return userOperationDao.findAll(where,pageable);
    }

    @Override
    public void delete(UserOperation userOperation){
        userOperationDao.delete(userOperation);
    }

    private Specification<UserOperation> getWhereClause(String keyword){
        return new Specification<UserOperation>() {
            @Override
            public Predicate toPredicate(Root<UserOperation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (keyword != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("userId"), StringUtils.trim(keyword))
                    );
                }
                return predicate;
            }
        };
    }
}
