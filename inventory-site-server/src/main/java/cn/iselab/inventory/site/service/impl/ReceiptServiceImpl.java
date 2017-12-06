package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.common.constanst.OrderNumConstants;
import cn.iselab.inventory.site.dao.ReceiptDao;
import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.model.Receipt;
import cn.iselab.inventory.site.service.ReceiptService;
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
 * @Date: Created in 下午4:08 2017/11/6
 * @Modified By:
 */
@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    ReceiptDao receiptDao;

    @Override
    public Receipt createReceipt(Receipt receipt){
        receipt.setDelete(DeleteStatus.IS_NOT_DELETE);
        receipt.setCreateTime(new Timestamp(System.currentTimeMillis()));
        receipt= receiptDao.save(receipt);
        receipt.setNumber(OrderNumConstants.SKD_ORDER+receipt.getId());
        return receipt;
    }

    @Override
    public Page<Receipt> getReceipts(String keyword, Pageable pageable){
        Specifications<Receipt> where=Specifications.where(getWhereClause(keyword));
        return receiptDao.findAll(where,pageable);
    }

    @Override
    public Receipt getReceipt(long receiptId){
        return receiptDao.findOne(receiptId);
    }

    @Override
    public Receipt getReceiptByNum(String number){
        return receiptDao.findByNumber(number);
    }

    @Override
    public void updateReceipt(Receipt receipt){
        receiptDao.save(receipt);
    }

    @Override
    public void deleteReceipt(Receipt receipt){
        receipt.setDelete(DeleteStatus.IS_DELETE);
        receiptDao.save(receipt);
    }

    private Specification<Receipt> getWhereClause(String keyword){
        return new Specification<Receipt>() {
            @Override
            public Predicate toPredicate(Root<Receipt> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (keyword != null) {
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("name"), StringUtils.trim(keyword))
                    );
                }
                predicate.getExpressions().add(
                        criteriaBuilder.equal(root.get("delete"), DeleteStatus.IS_NOT_DELETE)
                );
                return predicate;
            }
        };
    }
}
