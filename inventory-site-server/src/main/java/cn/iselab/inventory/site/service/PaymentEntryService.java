package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.GoodItem;
import cn.iselab.inventory.site.model.PaymentEntry;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:50 2017/11/6
 * @Modified By:
 */
public interface PaymentEntryService {

    PaymentEntry createPaymentEntry(PaymentEntry goodItem);

    List<PaymentEntry> getPaymentEntries(long paymentId);

    PaymentEntry getPaymentEntry(long id);

    void updatePaymentEntry(PaymentEntry goodItem);

    void deletePaymentEntry(PaymentEntry goodItem);
}
