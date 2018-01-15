package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.Receipt;
import cn.iselab.inventory.site.web.data.ReceiptVO;
import cn.iselab.inventory.site.web.data.TransferVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:49 2017/12/6
 * @Modified By:
 */
public interface ReceiptLogic {

    Page<ReceiptVO> getReceipts(String keyword, Pageable pageable);

    ReceiptVO getReceipt(String number);

    String createReceipt(ReceiptVO vo);

    void updateReceipt(ReceiptVO vo);

    void checkReceipt(Receipt receipt, List<TransferVO> vos);

    void deleteReceipt(String number);
}
