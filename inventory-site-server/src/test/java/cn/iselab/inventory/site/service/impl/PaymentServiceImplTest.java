package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.PaymentDao;
import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.service.PaymentService;
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
import org.springframework.data.jpa.domain.Specifications;
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
 * @Date: Created in 下午1:22 2017/11/7
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class PaymentServiceImplTest {

    @InjectMocks
    PaymentService paymentService=new PaymentServiceImpl();

    @Mock
    PaymentDao paymentDao;

    Payment payment=new Payment();
    List<Payment> payments=new ArrayList<>();
    Pageable pageable;
    Page<Payment> paymentPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        payment.setId(1L);
        payment.setAccount(1L);
        payment.setOperator("test");
        payment.setTotal(1.0);

        payments.add(payment);

        paymentPage=new PageImpl<Payment>(payments);

        pageable = new PageRequest(0,10);
    }

    @Test
    public void should_createPayment_when_givenPayment() throws Exception {
        when(paymentDao.save(any(Payment.class))).thenReturn(payment);

        Payment result=paymentService.createPayment(payment);

        Assert.assertEquals(payment.getId(),result.getId());
        Assert.assertEquals(payment.getAccount(),result.getAccount());
        Assert.assertEquals(payment.getOperator(),result.getOperator());
    }

    @Test
    public void should_returnPayments_when_givenKeyword() throws Exception {
        when(paymentDao.findAll(any(Specifications.class),any(Pageable.class))).thenReturn(paymentPage);

        Page<Payment> result=paymentService.getPayments("test",pageable);

        Assert.assertEquals(payment,result.getContent().get(0));
    }

    @Test
    public void should_returnPayment_when_givenPaymentId() throws Exception {
        when(paymentDao.findOne(anyLong())).thenReturn(payment);

        Payment result=paymentService.getPayment(1L);

        Assert.assertEquals(payment.getId(),result.getId());
        Assert.assertEquals(payment.getAccount(),result.getAccount());
        Assert.assertEquals(payment.getOperator(),result.getOperator());
    }

    @Test
    public void should_updatePayment_when_givenPayment() throws Exception {
        paymentService.updatePayment(payment);

        Mockito.verify(paymentDao).save(any(Payment.class));
    }

    @Test
    public void should_deletePayment_when_givenPayment() throws Exception {
        paymentService.deletePayment(payment);

        Mockito.verify(paymentDao).save(any(Payment.class));
    }

}