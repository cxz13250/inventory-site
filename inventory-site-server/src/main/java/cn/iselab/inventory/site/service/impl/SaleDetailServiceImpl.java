package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.SaleDetailDao;
import cn.iselab.inventory.site.model.Receipt;
import cn.iselab.inventory.site.model.SaleDetail;
import cn.iselab.inventory.site.service.SaleDetailService;
import org.apache.commons.lang.StringUtils;
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
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:48 2017/11/6
 * @Modified By:
 */
@Service
public class SaleDetailServiceImpl implements SaleDetailService {

    @Autowired
    SaleDetailDao saleDetailDao;

    @Override
    public SaleDetail createSaleDetail(SaleDetail saleDetail){
        return saleDetailDao.save(saleDetail);
    }

    @Override
    public List<SaleDetail> getAll(){
        return saleDetailDao.findAll();
    }

    @Override
    public Page<SaleDetail> getSaleDetails(String goodName,Pageable pageable){
        Specifications<SaleDetail> where=Specifications.where(getWhereClause(goodName));
        return saleDetailDao.findAll(where,pageable);
    }

    @Override
    public SaleDetail getSaleDetail(long saleDetailId){
        return saleDetailDao.findOne(saleDetailId);
    }

    @Override
    public void updateSaleDetail(SaleDetail saleDetail){
        saleDetailDao.save(saleDetail);
    }

    @Override
    public void deleteSaleDetail(SaleDetail saleDetail){
        saleDetailDao.delete(saleDetail);
    }

    private Specification<SaleDetail> getWhereClause(String goodName){
        return new Specification<SaleDetail>() {
            @Override
            public Predicate toPredicate(Root<SaleDetail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (goodName != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("goodName"), StringUtils.trim(goodName))
                    );
                }
                return predicate;
            }
        };
    }
}
