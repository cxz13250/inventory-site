package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.Payment;
import cn.iselab.inventory.site.web.data.PaymentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午9:59 2017/12/6
 * @Modified By:
 */
public interface PaymentLogic {

    Page<PaymentVO> getPayments(String keyword, Pageable pageable);

    PaymentVO getPayment(String number);

    String createPayment(PaymentVO vo);

    void checkPayment(Payment payment);

    void updatePayment(PaymentVO vo);

    void deletePayment(String number);
}
