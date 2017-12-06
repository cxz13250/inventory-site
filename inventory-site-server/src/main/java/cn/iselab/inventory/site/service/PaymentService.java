package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:46 2017/11/6
 * @Modified By:
 */
public interface PaymentService {

    Payment createPayment(Payment payment);

    Page<Payment> getPayments(String keyword, Pageable pageable);

    Payment getPayment(long paymentId);

    Payment getPaymentByNum(String number);

    void updatePayment(Payment payment);

    void deletePayment(Payment payment);
}
