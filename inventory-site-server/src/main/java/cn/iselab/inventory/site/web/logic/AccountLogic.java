package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.Account;
import cn.iselab.inventory.site.web.data.AccountVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:05 2017/12/4
 * @Modified By:
 */
public interface AccountLogic {

    Page<AccountVO> getAccounts(String keyword, Pageable pageable);

    AccountVO getAccount(Long accountId);

    Long createAccount(AccountVO accountVO);

    void updateAccount(AccountVO accountVO);

    void deleteAccount(Long accountId);
}
