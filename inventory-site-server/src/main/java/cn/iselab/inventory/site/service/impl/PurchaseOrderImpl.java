package cn.iselab.inventory.site.service.impl;

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

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:47 2017/11/6
 * @Modified By:
 */
@Service
public class PurchaseOrderImpl implements PurchaseOrderService {

    @Autowired
    PurchaseOrderDao purchaseOrderDao;

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder order){
        return purchaseOrderDao.save(order);
    }

    @Override
    public Page<PurchaseOrder> getPurchaseOrders(String keyword, Pageable pageable){
        Specifications<PurchaseOrder> where=Specifications.where(getWhereClause(keyword));
        return purchaseOrderDao.findAll(where, pageable);
    }

    @Override
    public PurchaseOrder getPurchaseOrder(long orderId){
        return purchaseOrderDao.findOne(orderId);
    }

    @Override
    public void updatePurchaseOrder(PurchaseOrder order){
        purchaseOrderDao.save(order);
    }

    @Override
    public void deletePurchaseOrder(PurchaseOrder order){
        purchaseOrderDao.delete(order);
    }

    private Specification<PurchaseOrder> getWhereClause(String keyword){
        return new Specification<PurchaseOrder>() {
            @Override
            public Predicate toPredicate(Root<PurchaseOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
