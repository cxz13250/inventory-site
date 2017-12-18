package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.common.constanst.OrderNumConstants;
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
import java.sql.Timestamp;

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
        payment.setDelete(DeleteStatus.IS_NOT_DELETE);
        payment.setCreateTime(new Timestamp(System.currentTimeMillis()));
        payment=paymentDao.save(payment);
        payment.setNumber(OrderNumConstants.XJFYD_ORDER+payment.getCreateTime());
        return payment;
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
    public Payment getPaymentByNum(String number){
        return paymentDao.findByNumber(number).get(0);
    }

    @Override
    public void updatePayment(Payment payment){
        paymentDao.save(payment);
    }

    @Override
    public void deletePayment(Payment payment){
        payment.setDelete(DeleteStatus.IS_DELETE);
        paymentDao.save(payment);
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
                predicate.getExpressions().add(
                        criteriaBuilder.equal(root.get("deleted"), DeleteStatus.IS_NOT_DELETE));
                return predicate;
            }
        };
    }
}
