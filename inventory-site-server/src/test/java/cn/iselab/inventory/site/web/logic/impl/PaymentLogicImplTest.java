package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.model.PaymentEntry;
import cn.iselab.inventory.site.service.PaymentEntryService;
import cn.iselab.inventory.site.service.PaymentService;
import cn.iselab.inventory.site.web.data.PaymentVO;
import cn.iselab.inventory.site.web.data.wrapper.PaymentVOWrapper;
import cn.iselab.inventory.site.web.logic.PaymentLogic;
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
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:29 2017/12/6
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class PaymentLogicImplTest {

    @InjectMocks
    PaymentLogic paymentLogic=new PaymentLogicImpl();

    @Mock
    PaymentVOWrapper paymentVOWrapper;

    @Mock
    PaymentService paymentService;

    @Mock
    PaymentEntryService paymentEntryService;

    Payment payment=new Payment();
    PaymentVO vo=new PaymentVO();
    PaymentEntry entry=new PaymentEntry();
    List<Payment> payments=new ArrayList<>();
    List<PaymentVO> paymentVOS=new ArrayList<>();
    List<PaymentEntry> entries=new ArrayList<>();
    Pageable pageable;
    Page<Payment> paymentPage;
    Page<PaymentVO> paymentVOPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        payment.setId(1L);
        payment.setOperator("test");
        payment.setTotal(1.0);
        payment.setNumber("test");

        payments.add(payment);

        vo.setOperator("test");
        vo.setTotal(1.0);
        vo.setNumber("test");
        vo.setStatus(1L);

        paymentVOS.add(vo);

        entry.setName("test");
        entry.setMoney(1.0);

        entries.add(entry);

        vo.setEntries(entries);

        paymentPage=new PageImpl<>(payments);
        paymentVOPage=new PageImpl<>(paymentVOS);

        pageable = new PageRequest(0,10);
    }

    @Test
    public void should_returnPayments_when_PaymentsExist() throws Exception {
        when(paymentService.getPayments(anyString(),any(Pageable.class))).thenReturn(paymentPage);
        when(paymentVOWrapper.wrap(any(Payment.class))).thenReturn(vo);

        Page<PaymentVO> result=paymentLogic.getPayments("test",pageable);
        Assert.assertEquals(vo.getOperator(),result.getContent().get(0).getOperator());
        Assert.assertEquals(vo.getNumber(),result.getContent().get(0).getNumber());
        Assert.assertEquals(vo.getTotal(),result.getContent().get(0).getTotal());
    }

    @Test
    public void should_returnPayment_when_givenPayment() throws Exception {
        when(paymentService.getPaymentByNum(anyString())).thenReturn(payment);
        when(paymentVOWrapper.wrap(any(Payment.class))).thenReturn(vo);

        PaymentVO result=paymentLogic.getPayment("test");

        Assert.assertEquals(vo.getNumber(),result.getNumber());
        Assert.assertEquals(vo.getOperator(),result.getOperator());
        Assert.assertEquals(vo.getTotal(),result.getTotal());
    }

    @Test
    public void should_createPayment_when_givenPayment() throws Exception {
        when(paymentVOWrapper.unwrap(any(PaymentVO.class))).thenReturn(payment);
        when(paymentService.createPayment(any(Payment.class))).thenReturn(payment);

        String result=paymentLogic.createPayment(vo);

        Mockito.verify(paymentEntryService).createPaymentEntry(any(PaymentEntry.class));
        Assert.assertEquals(payment.getNumber(),result);
    }

    @Test
    public void should_updatePayment_when_givenPayment() throws Exception {
        when(paymentService.getPaymentByNum(anyString())).thenReturn(payment);

        paymentLogic.updatePayment(vo);

        Mockito.verify(paymentService).updatePayment(any(Payment.class));
    }

    @Test
    public void should_deletePayment_when_givenPayment() throws Exception {
        when(paymentService.getPaymentByNum(anyString())).thenReturn(payment);

        paymentLogic.deletePayment("test");

        Mockito.verify(paymentService).deletePayment(any(Payment.class));
    }
}