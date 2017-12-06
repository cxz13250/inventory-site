package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Receipt;
import cn.iselab.inventory.site.web.data.ReceiptVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:50 2017/12/6
 * @Modified By:
 */
@Service
public class ReceiptVOWrapper extends BaseWrapper<ReceiptVO,Receipt> {

    @Override
    public ReceiptVO wrap(Receipt receipt){
        ReceiptVO vo = new ReceiptVO();
        vo.setNumber(receipt.getNumber());
        vo.setCreateTime(receipt.getCreateTime().getTime());
        vo.setOperator(receipt.getOperator());
        vo.setStatus(receipt.getStatus());
        vo.setTotal(receipt.getTotal());
        vo.setCreateTime(receipt.getCumstomId());
        return vo;
    }

    @Override
    public Receipt unwrap(ReceiptVO vo){
        Receipt receipt = new Receipt();
        receipt.setOperator(vo.getOperator());
        receipt.setTotal(vo.getTotal());
        receipt.setCumstomId(vo.getCumstomId());
        return receipt;
    }
}
