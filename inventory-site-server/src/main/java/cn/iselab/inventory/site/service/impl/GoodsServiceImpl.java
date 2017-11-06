package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.GoodsDao;
import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.service.GoodsService;
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

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:38 2017/11/6
 * @Modified By:
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsDao goodsDao;

    @Override
    public void createGood(Goods goods){
        goodsDao.save(goods);
    }

    @Override
    public Goods getGoodById(long goodId){
        return goodsDao.findOne(goodId);
    }

    @Override
    public Page<Goods> getGoods(String keyword, Pageable pageable){
        Specifications<Goods> where=Specifications.where(getWhereClause(keyword));
        return goodsDao.findAll(where,pageable);
    }

    @Override
    public void updateGood(Goods goods){
        goodsDao.save(goods);
    }

    @Override
    public void deleteGood(Goods goods){
        goodsDao.delete(goods);
    }

    private Specification<Goods> getWhereClause(String keyword){
        return new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (keyword != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.like(root.get("name"), "%" + StringUtils.trim(keyword) + "%")
                    );
                }
                return predicate;
            }
        };
    }
}
