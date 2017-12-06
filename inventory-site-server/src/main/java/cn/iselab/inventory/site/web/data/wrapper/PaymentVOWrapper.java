package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.web.data.PaymentVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:45 2017/12/6
 * @Modified By:
 */
@Service
public class PaymentVOWrapper extends BaseWrapper<PaymentVO,Payment>{

    @Override
    public PaymentVO wrap(Payment payment){
        PaymentVO vo = new PaymentVO();
        vo.setAccount(payment.getAccount());
        vo.setNumber(payment.getNumber());
        vo.setCreateTime(payment.getCreateTime().getTime());
        vo.setOperator(payment.getOperator());
        vo.setStatus(payment.getStatus());
        vo.setTotal(payment.getTotal());
        return vo;
    }

    @Override
    public Payment unwrap(PaymentVO vo){
        Payment payment = new Payment();
        payment.setAccount(vo.getAccount());
        payment.setStatus(vo.getStatus());
        payment.setOperator(vo.getOperator());
        payment.setTotal(vo.getTotal());
        return payment;
    }
}
