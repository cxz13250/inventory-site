package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Account;
import cn.iselab.inventory.site.web.data.AccountVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:14 2017/12/5
 * @Modified By:
 */
@Service
public class AccountVOWrapper extends BaseWrapper<AccountVO,Account>{

    @Override
    public AccountVO wrap(Account account){
        AccountVO vo=new AccountVO();
        vo.setId(account.getId());
        vo.setBalance(account.getBalance());
        vo.setBank(account.getBank());
        vo.setName(account.getName());
        return vo;
    }

    @Override
    public Account unwrap(AccountVO vo){
        Account account=new Account();
        account.setName(vo.getName());
        account.setBalance(vo.getBalance());
        account.setBank(vo.getBank());
        return account;
    }
}
