package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.PaymentEntryDao;
import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.model.PaymentEntry;
import cn.iselab.inventory.site.service.PaymentEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:50 2017/11/6
 * @Modified By:
 */
@Service
public class PaymentEntryServiceImpl implements PaymentEntryService {

    @Autowired
    private PaymentEntryDao paymentEntryDao;

    @Override
    public PaymentEntry createPaymentEntry(PaymentEntry PaymentEntry){
        return paymentEntryDao.save(PaymentEntry);
    }

    @Override
    public List<PaymentEntry> getPaymentEntries(long paymentId){
        return paymentEntryDao.findByPayment(paymentId);
    }

    @Override
    public PaymentEntry getPaymentEntry(long id){
        return paymentEntryDao.findOne(id);
    }

    @Override
    public void updatePaymentEntry(PaymentEntry PaymentEntry){
        paymentEntryDao.save(PaymentEntry);
    }

    @Override
    public void deletePaymentEntry(PaymentEntry PaymentEntry){
        paymentEntryDao.delete(PaymentEntry);
    }
}
