package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.Account;
import cn.iselab.inventory.site.service.AccountService;
import cn.iselab.inventory.site.web.data.AccountVO;
import cn.iselab.inventory.site.web.data.wrapper.AccountVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.AccountLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:05 2017/12/4
 * @Modified By:
 */
@Service
public class AccountLogicImpl implements AccountLogic{

    @Autowired
    AccountService accountService;

    @Autowired
    AccountVOWrapper accountVOWrapper;

    @Override
    public Page<AccountVO> getAccounts(String keyword, Pageable pageable){
        Page<Account> accounts=accountService.getAccounts(keyword,pageable);
        return accounts.map(new Converter<Account,AccountVO>() {
            @Override
            public AccountVO convert(Account custom) {
                return accountVOWrapper.wrap(custom);
            }
        });
    }

    @Override
    public AccountVO getAccount(Long accountId){
        Account account=accountService.getAccount(accountId);
        if(account==null){
            throw new HttpBadRequestException("account not exists");
        }
        return accountVOWrapper.wrap(account);
    }

    @Override
    public Long createAccount(AccountVO accountVO){
        Account account=accountVOWrapper.unwrap(accountVO);
        return accountService.create(account);
    }

    @Override
    public void updateAccount(AccountVO accountVO){
        Account account=accountService.getAccount(accountVO.getId());
        if(account==null){
            throw new HttpBadRequestException("custom not exists");
        }
        accountService.update(account,accountVO);
    }

    @Override
    public void deleteAccount(Long accountId){
        Account account=accountService.getAccount(accountId);
        if(account==null){
            throw new HttpBadRequestException("custom not exists");
        }
        accountService.delete(account);
    }

}
