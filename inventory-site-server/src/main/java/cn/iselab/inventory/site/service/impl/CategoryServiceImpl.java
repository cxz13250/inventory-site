package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.dao.CategoryDao;
import cn.iselab.inventory.site.model.Category;
import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.service.CategoryService;
import cn.iselab.inventory.site.web.data.CategoryVO;
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
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:45 2017/11/6
 * @Modified By:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public Category createCategory(Category category){
        category.setCreateTime(new Timestamp(System.currentTimeMillis()));
        category.setDeleted(DeleteStatus.IS_NOT_DELETE);
        return categoryDao.save(category);
    }

    @Override
    public Page<Category> getCategories(String keyword, Pageable pageable){
        Specifications<Category> where=Specifications.where(getWhereClause(keyword));
        return categoryDao.findAll(where,pageable);
    }

    @Override
    public Category getCategory(long id){
        return categoryDao.findOne(id);
    }

    @Override
    public List<Category> getCategoryByName(String name){
        List<Category> categories = categoryDao.findByName(name);
        return categories;
    }

    @Override
    public void deleteCategory(Category category){
        category.setDeleted(DeleteStatus.IS_DELETE);
        categoryDao.save(category);
    }

    @Override
    public void updateCategory(Category category, CategoryVO vo){
        category.setDescription(vo.getDescription());
        category.setName(vo.getName());
        category.setSuperId(vo.getSuperId());
        categoryDao.save(category);
    }

    @Override
    public List<Category> getCategoriesForGood(){
        return categoryDao.findForGood();
    }

    @Override
    public List<Category> getCategoriesForCategory(){
        return categoryDao.findForCategory();
    }

    @Override
    public List<Category> getCategoryBySuper(long superId){
        return categoryDao.findBySuperId(superId);
    }

    private Specification<Category> getWhereClause(String keyword){
        return new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (keyword != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.like(root.get("name"), "%" + StringUtils.trim(keyword) + "%")
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
