package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.common.constanst.OrderNumConstants;
import cn.iselab.inventory.site.common.constanst.OrderStatusConstants;
import cn.iselab.inventory.site.common.constanst.PurchaseOrderConstants;
import cn.iselab.inventory.site.dao.SaleOrderDao;
import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.service.SaleOrderService;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
 * @Date: Created in 下午2:43 2017/11/6
 * @Modified By:
 */
@Service
public class SaleOrderServiceImpl implements SaleOrderService {

    @Autowired
    SaleOrderDao saleOrderDao;

    @Override
    public SaleOrder createSaleOrder(SaleOrder saleOrder){
        saleOrder.setDeleted(DeleteStatus.IS_NOT_DELETE);
        saleOrder.setStatus(OrderStatusConstants.CHECKING);
        saleOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
        saleOrder= saleOrderDao.save(saleOrder);
        if(saleOrder.isType()== PurchaseOrderConstants.Output){
            saleOrder.setNumber(OrderNumConstants.XSD_ORDER+saleOrder.getCreateTime());
        }else {
            saleOrder.setNumber(OrderNumConstants.XSTHD_ORDER+saleOrder.getCreateTime());
        }
        return saleOrder;
    }

    @Override
    public Page<SaleOrder> getSaleOrders(String keyword, Pageable pageable,Boolean type){
        Specifications<SaleOrder> where=Specifications.where(getWhereClause(keyword,type));
        return saleOrderDao.findAll(where, pageable);
    }

    @Override
    public SaleOrder getSaleOrder(long saleOrderId){
        return saleOrderDao.findOne(saleOrderId);
    }

    @Override
    public SaleOrder getSaleOrderByNum(String number){
        return saleOrderDao.findByNumber(number);
    }

    @Override
    public void updateSaleOrder(SaleOrder saleOrder){
        saleOrderDao.save(saleOrder);
    }

    @Override
    public void deleteSaleOrder(SaleOrder saleOrder){
        saleOrder.setDeleted(DeleteStatus.IS_DELETE);
        saleOrderDao.save(saleOrder);
    }

    private Specification<SaleOrder> getWhereClause(String keyword, Boolean type){
        return new Specification<SaleOrder>() {
            @Override
            public Predicate toPredicate(Root<SaleOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
                        criteriaBuilder.equal(root.get("deleted"), DeleteStatus.IS_NOT_DELETE)
                );
                return predicate;
            }
        };
    }
}
