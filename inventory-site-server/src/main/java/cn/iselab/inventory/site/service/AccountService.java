package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Account;
import cn.iselab.inventory.site.web.data.AccountVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:45 2017/11/6
 * @Modified By:
 */
public interface AccountService {

    long create(Account account);

    Page<Account> getAccounts(String keyword, Pageable pageable);

    Account getAccount(long accountId);

    void update(Account account, AccountVO accountVO);

    void delete(Account account);
}
