package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.StockOrderDao;
import cn.iselab.inventory.site.model.StockOrder;
import cn.iselab.inventory.site.service.StockOrderService;
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
 * @Date: Created in 下午2:42 2017/11/6
 * @Modified By:
 */
@Service
public class StockOrderServiceImpl implements StockOrderService {

    @Autowired
    StockOrderDao stockOrderDao;

    @Override
    public StockOrder createStockOrder(StockOrder stockOrder){
        return stockOrderDao.save(stockOrder);
    }

    @Override
    public Page<StockOrder> getStockOrders(String keyword, Pageable pageable){
        Specifications<StockOrder> where=Specifications.where(getWhereClause(keyword));
        return stockOrderDao.findAll(where, pageable);
    }

    @Override
    public StockOrder getStockOrder(long stockOrderId){
        return stockOrderDao.findOne(stockOrderId);
    }

    @Override
    public void updateStockOrder(StockOrder stockOrder){
        stockOrderDao.save(stockOrder);
    }

    @Override
    public void deleteStockOrder(StockOrder stockOrder){
        stockOrderDao.delete(stockOrder);
    }

    private Specification<StockOrder> getWhereClause(String keyword){
        return new Specification<StockOrder>() {
            @Override
            public Predicate toPredicate(Root<StockOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
