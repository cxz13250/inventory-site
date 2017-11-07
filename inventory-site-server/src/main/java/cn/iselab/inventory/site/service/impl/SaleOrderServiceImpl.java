package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.SaleOrderDao;
import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.service.SaleOrderService;
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
 * @Date: Created in 下午2:43 2017/11/6
 * @Modified By:
 */
@Service
public class SaleOrderServiceImpl implements SaleOrderService {

    @Autowired
    SaleOrderDao saleOrderDao;

    @Override
    public SaleOrder createSaleOrder(SaleOrder saleOrder){
        return saleOrderDao.save(saleOrder);
    }

    @Override
    public Page<SaleOrder> getSaleOrders(String keyword, Pageable pageable){
        Specifications<SaleOrder> where=Specifications.where(getWhereClause(keyword));
        return saleOrderDao.findAll(where, pageable);
    }

    @Override
    public SaleOrder getSaleOrder(long saleOrderId){
        return saleOrderDao.findOne(saleOrderId);
    }

    @Override
    public void updateSaleOrder(SaleOrder saleOrder){
        saleOrderDao.save(saleOrder);
    }

    @Override
    public void deleteSaleOrder(SaleOrder saleOrder){
        saleOrderDao.delete(saleOrder);
    }

    private Specification<SaleOrder> getWhereClause(String keyword){
        return new Specification<SaleOrder>() {
            @Override
            public Predicate toPredicate(Root<SaleOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (keyword != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("number"), StringUtils.trim(keyword))
                    );
                }
                return predicate;
            }
        };
    }
}
