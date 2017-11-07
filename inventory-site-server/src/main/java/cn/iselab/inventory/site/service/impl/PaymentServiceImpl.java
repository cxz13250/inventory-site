package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.PaymentDao;
import cn.iselab.inventory.site.model.Custom;
import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.service.PaymentService;
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
 * @Date: Created in 下午2:46 2017/11/6
 * @Modified By:
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Override
    public Payment createPayment(Payment payment){
        return paymentDao.save(payment);
    }

    @Override
    public Page<Payment> getPayments(String keyword, Pageable pageable){
        Specifications<Payment> where=Specifications.where(getWhereClause(keyword));
        return paymentDao.findAll(where, pageable);
    }

    @Override
    public Payment getPayment(long paymentId){
        return paymentDao.findOne(paymentId);
    }

    @Override
    public void updatePayment(Payment payment){
        paymentDao.save(payment);
    }

    @Override
    public void deletePayment(Payment payment){
        paymentDao.delete(payment);
    }

    private Specification<Payment> getWhereClause(String keyword){
        return new Specification<Payment>() {
            @Override
            public Predicate toPredicate(Root<Payment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
