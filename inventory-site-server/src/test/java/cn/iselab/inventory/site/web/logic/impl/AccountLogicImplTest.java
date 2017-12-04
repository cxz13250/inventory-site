package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.model.Account;
import cn.iselab.inventory.site.service.AccountService;
import cn.iselab.inventory.site.web.data.AccountVO;
import cn.iselab.inventory.site.web.data.wrapper.AccountVOWrapper;
import cn.iselab.inventory.site.web.logic.AccountLogic;
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
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:28 2017/12/5
 * @Modified By:
 */

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class AccountLogicImplTest {

    @InjectMocks
    AccountLogic accountLogic=new AccountLogicImpl();

    @Mock
    AccountService accountService;

    @Mock
    AccountVOWrapper accountVOWrapper;


    Page<AccountVO> accountVOPage;
    Page<Account> accountPage;
    List<AccountVO> accountVOS=new ArrayList<>();
    List<Account> accounts=new ArrayList<>();
    AccountVO vo=new AccountVO();
    Account account=new Account();
    Pageable pageable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        account.setId(1L);
        account.setName("test");
        account.setBank("test");
        account.setBalance(2000.0);

        accounts.add(account);

        vo.setId(1L);
        vo.setName("test");
        vo.setBank("test");
        vo.setBalance(2000.0);

        accountVOS.add(vo);

        accountVOPage=new PageImpl<AccountVO>(accountVOS);
        accountPage=new PageImpl<Account>(accounts);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_returnAccounts_when_AccountsExists() throws Exception {
        when(accountService.getAccounts(anyString(),any(Pageable.class))).thenReturn(accountPage);
        when(accountVOWrapper.wrap(any(Account.class))).thenReturn(vo);

        Page<AccountVO> result=accountLogic.getAccounts("test",pageable);
        Assert.assertEquals(vo.getName(),result.getContent().get(0).getName());
        Assert.assertEquals(vo.getBank(),result.getContent().get(0).getBank());
        Assert.assertEquals(vo.getBalance(),result.getContent().get(0).getBalance());
    }

    @Test
    public void should_returnAccount_when_givenAccountId() throws Exception {
        when(accountService.getAccount(anyLong())).thenReturn(account);
        when(accountVOWrapper.wrap(any(Account.class))).thenReturn(vo);

        AccountVO result=accountLogic.getAccount(1L);
        Assert.assertEquals(vo.getName(),result.getName());
        Assert.assertEquals(vo.getBank(),result.getBank());
        Assert.assertEquals(vo.getBalance(),result.getBalance());
    }

    @Test
    public void should_createAccount_when_givenAccount() throws Exception {
        when(accountVOWrapper.unwrap(any(AccountVO.class))).thenReturn(account);
        when(accountService.create(any(Account.class))).thenReturn(1L);

        Long result=accountLogic.createAccount(vo);

        Assert.assertEquals((Long)account.getId(),result);
    }

    @Test
    public void should_updateAccount_when_givenAccount() throws Exception {
        when(accountService.getAccount(anyLong())).thenReturn(account);

        accountLogic.updateAccount(vo);

        Mockito.verify(accountService).update(any(Account.class),any(AccountVO.class));
    }

    @Test
    public void should_deleteAccount_when_givenAccountId() throws Exception {
        when(accountService.getAccount(anyLong())).thenReturn(account);

        accountLogic.deleteAccount(1L);

        Mockito.verify(accountService).delete(any(Account.class));
    }

}