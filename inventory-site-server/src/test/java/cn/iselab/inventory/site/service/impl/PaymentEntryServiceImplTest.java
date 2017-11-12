package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.PaymentEntryDao;
import cn.iselab.inventory.site.model.PaymentEntry;
import cn.iselab.inventory.site.service.PaymentEntryService;
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
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:15 2017/11/13
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class PaymentEntryServiceImplTest {
    
    @InjectMocks
    private PaymentEntryService paymentEntryService=new PaymentEntryServiceImpl();
    
    @Mock
    private PaymentEntryDao paymentEntryDao;
    
    PaymentEntry paymentEntry=new PaymentEntry();
    List<PaymentEntry> paymentEntries=new ArrayList<>();
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        paymentEntry.setId(1L);
        paymentEntry.setMoney(1.0);
        paymentEntry.setName("test");
        paymentEntry.setPayment(1L);
        
        paymentEntries.add(paymentEntry);
    }

    @Test
    public void should_createpaymentEntry_when_givenpaymentEntry() throws Exception {
        when(paymentEntryDao.save(any(PaymentEntry.class))).thenReturn(paymentEntry);

        PaymentEntry result=paymentEntryService.createPaymentEntry(paymentEntry);

        Assert.assertEquals(paymentEntry.getId(),result.getId());
        Assert.assertEquals(paymentEntry.getName(),result.getName());
        Assert.assertEquals(paymentEntry.getPayment(),result.getPayment());
    }

    @Test
    public void should_returnpaymentEntrys_when_givenOrderId() throws Exception {
        when(paymentEntryDao.findByPayment(anyLong())).thenReturn(paymentEntries);

        List<PaymentEntry> result=paymentEntryService.getPaymentEntries(1L);

        Assert.assertEquals(result.get(0),paymentEntries.get(0));
    }

    @Test
    public void should_returnpaymentEntry_when_givenpaymentEntry() throws Exception {
        when(paymentEntryDao.findOne(anyLong())).thenReturn(paymentEntry);

        PaymentEntry result=paymentEntryService.getPaymentEntry(1L);

        Assert.assertEquals(paymentEntry.getId(),result.getId());
        Assert.assertEquals(paymentEntry.getName(),result.getName());
        Assert.assertEquals(paymentEntry.getPayment(),result.getPayment());
    }

    @Test
    public void should_updatepaymentEntry_when_givenpaymentEntry() throws Exception {
        paymentEntryService.updatePaymentEntry(paymentEntry);

        Mockito.verify(paymentEntryDao).save(any(PaymentEntry.class));
    }

    @Test
    public void should_deletepaymentEntry_when_givenpaymentEntry() throws Exception {
        paymentEntryService.deletePaymentEntry(paymentEntry);

        Mockito.verify(paymentEntryDao).delete(any(PaymentEntry.class));
    }
}