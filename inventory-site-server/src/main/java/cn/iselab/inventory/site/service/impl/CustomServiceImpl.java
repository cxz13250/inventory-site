package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.dao.CustomDao;
import cn.iselab.inventory.site.model.Account;
import cn.iselab.inventory.site.model.Custom;
import cn.iselab.inventory.site.service.CustomService;
import cn.iselab.inventory.site.web.data.CustomVO;
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
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:39 2017/11/6
 * @Modified By:
 */
@Service
public class CustomServiceImpl implements CustomService {

    @Autowired
    private CustomDao customDao;

    @Override
    public long createCustom(Custom custom){
        custom.setDelete(DeleteStatus.IS_NOT_DELETE);
        custom.setCreateTime(new Timestamp(System.currentTimeMillis()));
        custom=customDao.save(custom);
        return custom.getId();
    }

    @Override
    public Custom getCustom(long customId){
        return customDao.findOne(customId);
    }

    @Override
    public Page<Custom> getCustoms(String keywrod, Pageable pageable){
        Specifications<Custom> where=Specifications.where(getWhereClause(keywrod));
        return customDao.findAll(where, pageable);
    }

    @Override
    public List<Custom> getCustomsForReceipt(){
        return customDao.getAll();
    }

    @Override
    public List<Custom> getCustomsForSale(){
        return customDao.getAllForSale();
    }

    @Override
    public List<Custom> getCustomsForPurchase(){
        return customDao.getAllForPurchase();
    }

    @Override
    public void updateCustom(Custom custom, CustomVO vo){
        custom.setEmail(vo.getEmail());
        custom.setAddress(vo.getAddress());
        custom.setPostCode(vo.getPostCode());
        if(vo.getSalesman()!=null)
            custom.setSalesman(vo.getSalesman());
        if(vo.getReceive()!=null)
            custom.setReceive(vo.getReceive());
        if(vo.getReceiveLimit()!=null)
            custom.setReceiveLimit(vo.getReceiveLimit());
        custom.setLevel(vo.getLevel());
        custom.setMobile(vo.getMobile());
        if(vo.getPay()!=null)
            custom.setPay(vo.getPay());
        custom.setName(vo.getName());
        customDao.save(custom);
    }

    @Override
    public void updateCustom2(Custom custom){
        customDao.save(custom);
    }

    @Override
    public void deletCustom(Custom custom){
        custom.setDelete(DeleteStatus.IS_DELETE);
        customDao.save(custom);
    }

    private Specification<Custom> getWhereClause(String keyword){
        return new Specification<Custom>() {
            @Override
            public Predicate toPredicate(Root<Custom> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (keyword != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.like(root.get("name"), "%" + StringUtils.trim(keyword) + "%")
                    );
                }
                predicate.getExpressions().add(
                        criteriaBuilder.equal(root.get("isDelete"), DeleteStatus.IS_NOT_DELETE)
                );
                return predicate;
            }
        };
    }
}
