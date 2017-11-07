package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.ReceiptDao;
import cn.iselab.inventory.site.model.Receipt;
import cn.iselab.inventory.site.service.ReceiptService;
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
 * @Date: Created in 下午1:46 2017/11/7
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class ReceiptServiceImplTest {

    @InjectMocks
    ReceiptService receiptService=new ReceiptServiceImpl();

    @Mock
    ReceiptDao receiptDao;

    Receipt receipt=new Receipt();
    List<Receipt> receipts=new ArrayList<>();
    Pageable pageable;
    Page<Receipt> receiptPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        receipt.setId(1L);
        receipt.setCumstomId(1L);
        receipt.setOperator(1L);
        receipt.setNumber("test");

        receipts.add(receipt);

        receiptPage=new PageImpl<Receipt>(receipts);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_createReceipt_when_givenReceipt() throws Exception {
        when(receiptDao.save(any(Receipt.class))).thenReturn(receipt);

        Receipt result=receiptService.createReceipt(receipt);

        Assert.assertEquals(receipt.getId(),result.getId());
        Assert.assertEquals(receipt.getCumstomId(),result.getCumstomId());
        Assert.assertEquals(receipt.getOperator(),result.getOperator());
        Assert.assertEquals(receipt.getNumber(),result.getNumber());
    }

    @Test
    public void should_returnReceipts_when_givenKeyword() throws Exception {
        when(receiptDao.findAll(any(Specifications.class),any(Pageable.class))).thenReturn(receiptPage);

        Page<Receipt> result=receiptService.getReceipts("test",pageable);

        Assert.assertEquals(receipt,result.getContent().get(0));
    }

    @Test
    public void should_returnReceipt_when_givenReceiptId() throws Exception {
        when(receiptDao.findOne(anyLong())).thenReturn(receipt);

        Receipt result=receiptService.getReceipt(1L);

        Assert.assertEquals(receipt.getId(),result.getId());
        Assert.assertEquals(receipt.getCumstomId(),result.getCumstomId());
        Assert.assertEquals(receipt.getOperator(),result.getOperator());
        Assert.assertEquals(receipt.getNumber(),result.getNumber());
    }

    @Test
    public void should_updateReceipt_when_givenReceipt() throws Exception {
        receiptService.updateReceipt(receipt);

        Mockito.verify(receiptDao).save(any(Receipt.class));
    }

    @Test
    public void should_deleteReceipt_when_givenReceipt() throws Exception {
        receiptService.deleteReceipt(receipt);

        Mockito.verify(receiptDao).delete(any(Receipt.class));
    }

}