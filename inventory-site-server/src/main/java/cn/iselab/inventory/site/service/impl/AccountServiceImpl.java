package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.service.AccountService;
import cn.iselab.inventory.site.dao.AccountDao;
import cn.iselab.inventory.site.model.Account;
import cn.iselab.inventory.site.web.data.AccountVO;
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
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:46 2017/11/6
 * @Modified By:
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public long create(Account account){
        account.setDeleted(DeleteStatus.IS_NOT_DELETE);
        account.setCreateTime(new Timestamp(System.currentTimeMillis()));
        account=accountDao.save(account);
        return account.getId();
    }

    @Override
    public Page<Account> getAccounts(String keyword, Pageable pageable){
        Specifications<Account> where=Specifications.where(getWhereClause(keyword));
        return accountDao.findAll(where,pageable);
    }

    @Override
    public List<Account> getAccountsForReceipt(){
        return accountDao.getAll();
    }

    @Override
    public Account getAccount(long accountId){
        return accountDao.findOne(accountId);
    }

    @Override
    public Account getAccountByName(String accountName){
        return accountDao.findByName(accountName).get(0);
    }

    @Override
    public Account updateAccount(Account account){
        return accountDao.save(account);
    }

    @Override
    public void update(Account account, AccountVO accountVO){
        account.setBank(accountVO.getBank());
        account.setName(accountVO.getName());
        account.setBalance(accountVO.getBalance());
        account.setBankNum(accountVO.getBankNum());
        accountDao.save(account);
    }

    @Override
    public void delete(Account account){
        account.setDeleted(DeleteStatus.IS_DELETE);
        accountDao.save(account);
    }

    private Specification<Account> getWhereClause(String keyword){
        return new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
