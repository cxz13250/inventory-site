package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.service.AccountService;
import cn.iselab.inventory.site.web.data.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:45 2017/12/6
 * @Modified By:
 */
@Service
public class PaymentVOWrapper extends BaseWrapper<PaymentVO,Payment>{

    @Autowired
    AccountService accountService;

    @Override
    public PaymentVO wrap(Payment payment){
        PaymentVO vo = new PaymentVO();
        vo.setAccountId(payment.getAccount());
        vo.setAccount(accountService.getAccount(payment.getAccount()).getName());
        vo.setNumber(payment.getNumber());
        vo.setCreateTime(payment.getCreateTime().getTime());
        vo.setOperator(payment.getOperator());
        vo.setCustomId(payment.getCustom());
        vo.setStatus(payment.getStatus());
        vo.setTotal(payment.getTotal());
        return vo;
    }

    @Override
    public Payment unwrap(PaymentVO vo){
        Payment payment = new Payment();
        payment.setAccount(vo.getAccountId());
        payment.setStatus(vo.getStatus());
        payment.setOperator(vo.getOperator());
        payment.setTotal(vo.getTotal());
        payment.setCustom(vo.getCustomId());
        return payment;
    }
}
