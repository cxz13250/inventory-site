package cn.iselab.inventory.site;

import cn.iselab.inventory.site.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:45 2017/11/6
 * @Modified By:
 */
public interface AccountService {

    public long create(Account account);

    public Page<Account> getAccounts(String keyword, Pageable pageable);

    public Account getAccount(long accountId);

    public void update(Account account);

    public void delete(Account account);
}
