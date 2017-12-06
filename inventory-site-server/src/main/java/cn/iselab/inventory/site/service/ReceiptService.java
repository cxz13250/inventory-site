package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午4:07 2017/11/6
 * @Modified By:
 */
public interface ReceiptService {

    Receipt createReceipt(Receipt receipt);

    Page<Receipt> getReceipts(String keyword, Pageable pageable);

    Receipt getReceipt(long receiptId);

    Receipt getReceiptByNum(String number);

    void updateReceipt(Receipt receipt);

    void deleteReceipt(Receipt receipt);
}
