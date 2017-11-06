package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.AccountDao;
import cn.iselab.inventory.site.service.AccountService;
import cn.iselab.inventory.site.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午6:29 2017/11/6
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class AccountServiceImplTest {

    @InjectMocks
    AccountService accountService=new AccountServiceImpl();

    @Mock
    AccountDao accountDao;

    Account account=new Account();
    List<Account> accounts=new ArrayList<>();
    Pageable pageable;

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        account.setId(1L);
        account.setName("test");
        account.setBank("test");
        account.setBalance(2000.0);

        accounts.add(account);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_createAccount_when_givenAccount() throws Exception {
        Account expect=new Account();
        expect.setName("test");
        when(accountDao.save(any(Account.class))).thenReturn(account);

        long result=accountService.create(expect);

        Assert.assertEquals(account.getId(),result);
    }

    @Test
    public void should_returnAccounts_when_givenKeyword() throws Exception {
        Page<Account> accountPage=new PageImpl<Account>(accounts);
        when(accountDao.findAll(any(Specification.class),any(Pageable.class))).thenReturn(accountPage);

        Page<Account> result=accountService.getAccounts("test",pageable);

        Assert.assertEquals(accounts,result.getContent());
    }

    @Test
    public void should_returnAccount_when_givenAccountId() throws Exception {
        when(accountDao.findOne(anyLong())).thenReturn(account);

        Account result=accountService.getAccount(1L);

        Assert.assertEquals(result.getId(),account.getId());
        Assert.assertEquals(result.getName(),account.getName());
        Assert.assertEquals(result.getBank(),account.getBank());
        Assert.assertEquals(result.getBalance(),account.getBalance());
    }

    @Test
    public void should_updateAccount_when_givenAccount() throws Exception {
        accountService.update(account);

        Mockito.verify(accountDao).save(any(Account.class));
    }

    @Test
    public void should_deleteAccount_when_deleteAccount() throws Exception {
        accountService.delete(account);

        Mockito.verify(accountDao).delete(any(Account.class));
    }

}