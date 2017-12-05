package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.common.constanst.OrderNumConstants;
import cn.iselab.inventory.site.common.constanst.PurchaseOrderConstants;
import cn.iselab.inventory.site.dao.PurchaseOrderDao;
import cn.iselab.inventory.site.model.PurchaseOrder;
import cn.iselab.inventory.site.service.PurchaseOrderService;
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

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:47 2017/11/6
 * @Modified By:
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    PurchaseOrderDao purchaseOrderDao;

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder order){
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setDelete(DeleteStatus.IS_NOT_DELETE);
        order=purchaseOrderDao.save(order);
        if(order.isType()== PurchaseOrderConstants.Input) {
            order.setNumber(OrderNumConstants.JHD_ORDER+order.getId());
        }else {
            order.setNumber(OrderNumConstants.JHTHD_ORDER+order.getId());
        }
        return order;
    }

    @Override
    public Page<PurchaseOrder> getPurchaseOrders(String keyword, Pageable pageable, Boolean type){
        Specifications<PurchaseOrder> where=Specifications.where(getWhereClause(keyword,type));
        return purchaseOrderDao.findAll(where, pageable);
    }

    @Override
    public PurchaseOrder getPurchaseOrder(long orderId){
        return purchaseOrderDao.findOne(orderId);
    }

    @Override
    public PurchaseOrder getPurchaseOrderByNum(String number){
        return purchaseOrderDao.findByNumber(number);
    }

    @Override
    public void updatePurchaseOrder(PurchaseOrder order){
        purchaseOrderDao.save(order);
    }

    @Override
    public void deletePurchaseOrder(PurchaseOrder order){
        order.setDelete(DeleteStatus.IS_DELETE);
        purchaseOrderDao.save(order);
    }

    private Specification<PurchaseOrder> getWhereClause(String keyword,Boolean type){
        return new Specification<PurchaseOrder>() {
            @Override
            public Predicate toPredicate(Root<PurchaseOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (keyword != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("number"), StringUtils.trim(keyword))
                    );
                }
                if (type != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("type"), type)
                    );
                }
                predicate.getExpressions().add(
                        criteriaBuilder.equal(root.get("delete"), DeleteStatus.IS_NOT_DELETE)
                );
                return predicate;
            }
        };
    }
}
