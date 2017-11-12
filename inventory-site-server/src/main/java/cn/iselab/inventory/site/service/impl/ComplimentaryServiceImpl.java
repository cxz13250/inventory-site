package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.ComplimentaryDao;
import cn.iselab.inventory.site.model.Complimentary;
import cn.iselab.inventory.site.model.SaleStrategy;
import cn.iselab.inventory.site.service.ComplimentaryService;
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
 * @Date: Created in 下午2:53 2017/11/6
 * @Modified By:
 */
@Service
public class ComplimentaryServiceImpl implements ComplimentaryService {

    @Autowired
    private ComplimentaryDao complimentaryDao;

    @Override
    public Complimentary createComplimentary(Complimentary complimentary){
        return complimentaryDao.save(complimentary);
    }

    @Override
    public Page<Complimentary> getComplimentaries(String keyword, Pageable pageable){
        Specifications<Complimentary> where=Specifications.where(getWhereClause(keyword));
        return complimentaryDao.findAll(where, pageable);
    }
    @Override
    public Complimentary getComplimentary(long complimentaryId){
        return complimentaryDao.findOne(complimentaryId);
    }

    @Override
    public void updateComplimentary(Complimentary complimentary){
        complimentaryDao.save(complimentary);
    }

    @Override
    public void deleteComplimentary(Complimentary complimentary){
        complimentaryDao.delete(complimentary);
    }

    private Specification<Complimentary> getWhereClause(String keyword){
        return new Specification<Complimentary>() {
            @Override
            public Predicate toPredicate(Root<Complimentary> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (keyword != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("name"), "%"+ StringUtils.trim(keyword)+"%")
                    );
                }
                return predicate;
            }
        };
    }
}
