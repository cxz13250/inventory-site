package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.model.Receipt;
import cn.iselab.inventory.site.service.ReceiptService;
import cn.iselab.inventory.site.web.data.PaymentVO;
import cn.iselab.inventory.site.web.data.ReceiptVO;
import cn.iselab.inventory.site.web.data.wrapper.ReceiptVOWrapper;
import cn.iselab.inventory.site.web.logic.ReceiptLogic;
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
 * @Date: Created in 上午12:21 2017/12/7
 * @Modified By:
 */

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class ReceiptLogicImplTest {

    @InjectMocks
    ReceiptLogic receiptLogic=new ReceiptLogicImpl();

    @Mock
    ReceiptVOWrapper receiptVOWrapper;

    @Mock
    ReceiptService receiptService;

    Receipt receipt=new Receipt();
    ReceiptVO vo=new ReceiptVO();
    List<Receipt> receipts=new ArrayList<>();
    List<ReceiptVO> receiptVOS=new ArrayList<>();
    Pageable pageable;
    Page<Receipt> receiptPage;
    Page<ReceiptVO> receiptVOPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        receipt.setId(1L);
        receipt.setOperator("test");
        receipt.setTotal(1.0);
        receipt.setNumber("test");
        receipt.setCumstomId(1L);

        receipts.add(receipt);

        vo.setOperator("test");
        vo.setTotal(1.0);
        vo.setNumber("test");
        vo.setCustomId(1L);
        vo.setStatus(1L);

        receiptVOS.add(vo);

        receiptPage=new PageImpl<>(receipts);
        receiptVOPage=new PageImpl<>(receiptVOS);

        pageable = new PageRequest(0,10);
    }

    @Test
    public void should_returnReceipts_when_ReceiptsExist() throws Exception {
        when(receiptService.getReceipts(anyString(),any(Pageable.class))).thenReturn(receiptPage);
        when(receiptVOWrapper.wrap(any(Receipt.class))).thenReturn(vo);

        Page<ReceiptVO> result=receiptLogic.getReceipts("test",pageable);
        Assert.assertEquals(vo.getOperator(),result.getContent().get(0).getOperator());
        Assert.assertEquals(vo.getNumber(),result.getContent().get(0).getNumber());
        Assert.assertEquals(vo.getTotal(),result.getContent().get(0).getTotal());
    }

    @Test
    public void should_returnReceipt_when_givenReceiptNumber() throws Exception {
        when(receiptService.getReceiptByNum(anyString())).thenReturn(receipt);
        when(receiptVOWrapper.wrap(any(Receipt.class))).thenReturn(vo);

        ReceiptVO result=receiptLogic.getReceipt("test");

        Assert.assertEquals(vo.getNumber(),result.getNumber());
        Assert.assertEquals(vo.getOperator(),result.getOperator());
        Assert.assertEquals(vo.getTotal(),result.getTotal());
    }

    @Test
    public void should_createReceipt_when_givenReceipt() throws Exception {
        when(receiptVOWrapper.unwrap(any(ReceiptVO.class))).thenReturn(receipt);
        when(receiptService.createReceipt(any(Receipt.class))).thenReturn(receipt);

        String result=receiptLogic.createReceipt(vo);

        Assert.assertEquals(receipt.getNumber(),result);
    }

    @Test
    public void should_updateReceipt_when_givenReceipt() throws Exception {
        when(receiptService.getReceiptByNum(anyString())).thenReturn(receipt);

        receiptLogic.updateReceipt(vo);

        Mockito.verify(receiptService).updateReceipt(any(Receipt.class));
    }

    @Test
    public void should_deleteReceipt_when_givenReceipt() throws Exception {
        when(receiptService.getReceiptByNum(anyString())).thenReturn(receipt);

        receiptLogic.deleteReceipt("test");

        Mockito.verify(receiptService).deleteReceipt(any(Receipt.class));
    }
}