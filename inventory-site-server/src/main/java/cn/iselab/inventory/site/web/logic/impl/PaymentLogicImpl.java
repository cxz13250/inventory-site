package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.service.PaymentService;
import cn.iselab.inventory.site.web.data.PaymentVO;
import cn.iselab.inventory.site.web.data.wrapper.PaymentVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.PaymentLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午9:59 2017/12/6
 * @Modified By:
 */
@Service
public class PaymentLogicImpl implements PaymentLogic{

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentVOWrapper paymentVOWrapper;

    @Override
    public Page<PaymentVO> getPayments(String keyword, Pageable pageable){
        Page<Payment> payments=paymentService.getPayments(keyword,pageable);
        return payments.map(new Converter<Payment, PaymentVO>() {
            @Override
            public PaymentVO convert(Payment payment) {
                return paymentVOWrapper.wrap(payment);
            }
        });
    }

    @Override
    public PaymentVO getPayment(String number){
        Payment payment=paymentService.getPaymentByNum(number);
        if (payment == null) {
            throw new HttpBadRequestException("payment not exists");
        }
        return paymentVOWrapper.wrap(payment);
    }

    @Override
    public String createPayment(PaymentVO vo){
        Payment payment=paymentVOWrapper.unwrap(vo);
        payment=paymentService.createPayment(payment);
        return payment.getNumber();
    }

    @Override
    public void updatePayment(PaymentVO vo){
        Payment payment=paymentService.getPaymentByNum(vo.getNumber());
        if (payment == null) {
            throw new HttpBadRequestException("payment not exists");
        }
        updateInfo(payment,vo);
        paymentService.updatePayment(payment);
    }

    @Override
    public void deletePayment(String number){
        Payment payment=paymentService.getPaymentByNum(number);
        if (payment == null) {
            throw new HttpBadRequestException("payment not exists");
        }
        paymentService.deletePayment(payment);
    }

    private void updateInfo(Payment payment,PaymentVO vo){
        payment.setTotal(vo.getTotal());
        if (vo.getAccount()!=null) {
            payment.setAccount(vo.getAccount());
        }
    }

}
