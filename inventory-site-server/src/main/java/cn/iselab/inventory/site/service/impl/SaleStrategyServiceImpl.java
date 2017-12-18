package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.dao.SaleStrategyDao;
import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.model.SaleStrategy;
import cn.iselab.inventory.site.service.SaleStrategyService;
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
 * @Date: Created in 下午2:49 2017/11/6
 * @Modified By:
 */
@Service
public class SaleStrategyServiceImpl implements SaleStrategyService {

    @Autowired
    SaleStrategyDao saleStrategyDao;

    @Override
    public SaleStrategy createSaleStrategy(SaleStrategy strategy){
        strategy.setDelete(DeleteStatus.IS_NOT_DELETE);
        return saleStrategyDao.save(strategy);
    }

    @Override
    public Page<SaleStrategy> getSaleStrategies(String keyword, Pageable pageable){
        Specifications<SaleStrategy> where=Specifications.where(getWhereClause(keyword));
        return saleStrategyDao.findAll(where, pageable);
    }

    @Override
    public SaleStrategy getSaleStrategy(long saleStrategyId){
        return saleStrategyDao.findOne(saleStrategyId);
    }

    @Override
    public void updateSaleStrategy(SaleStrategy strategy){
        saleStrategyDao.save(strategy);
    }

    @Override
    public void deleteSaleStrategy(SaleStrategy strategy){
        saleStrategyDao.delete(strategy);
    }

    private Specification<SaleStrategy> getWhereClause(String keyword){
        return new Specification<SaleStrategy>() {
            @Override
            public Predicate toPredicate(Root<SaleStrategy> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (keyword != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("name"), "%"+StringUtils.trim(keyword)+"%")
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
