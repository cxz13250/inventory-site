package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.Receipt;
import cn.iselab.inventory.site.service.ReceiptService;
import cn.iselab.inventory.site.web.data.ReceiptVO;
import cn.iselab.inventory.site.web.data.wrapper.ReceiptVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.ReceiptLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:49 2017/12/6
 * @Modified By:
 */
@Service
public class ReceiptLogicImpl implements ReceiptLogic{

    @Autowired
    ReceiptService receiptService;

    @Autowired
    ReceiptVOWrapper receiptVOWrapper;

    @Override
    public Page<ReceiptVO> getReceipts(String keyword, Pageable pageable){
        Page<Receipt> receipts=receiptService.getReceipts(keyword,pageable);
        return receipts.map(new Converter<Receipt, ReceiptVO>() {
            @Override
            public ReceiptVO convert(Receipt receipt) {
                return receiptVOWrapper.wrap(receipt);
            }
        });
    }

    @Override
    public ReceiptVO getReceipt(String number){
        Receipt receipt=receiptService.getReceiptByNum(number);
        if(receipt==null){
            throw new HttpBadRequestException("receipt not exists");
        }
        return receiptVOWrapper.wrap(receipt);
    }

    @Override
    public String createReceipt(ReceiptVO vo){
        Receipt receipt=receiptVOWrapper.unwrap(vo);
        receipt=receiptService.createReceipt(receipt);
        return receipt.getNumber();
    }

    @Override
    public void updateReceipt(ReceiptVO vo){
        Receipt receipt=receiptService.getReceiptByNum(vo.getNumber());
        if(receipt==null){
            throw new HttpBadRequestException("receipt not exists");
        }
        receiptService.updateReceipt(receipt);
    }

    @Override
    public void deleteReceipt(String number){
        Receipt receipt=receiptService.getReceiptByNum(number);
        if(receipt==null){
            throw new HttpBadRequestException("receipt not exists");
        }
        receiptService.deleteReceipt(receipt);
    }

    private void updateInfo(Receipt receipt,ReceiptVO vo){
        receipt.setTotal(vo.getTotal());
        if(vo.getCumstomId()!=null){
            receipt.setCumstomId(vo.getCumstomId());
        }
        if (vo.getOperator() != null) {
            receipt.setOperator(vo.getOperator());
        }
    }
}
