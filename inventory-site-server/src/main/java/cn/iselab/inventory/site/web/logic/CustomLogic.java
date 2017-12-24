package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.web.data.CustomVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:05 2017/12/4
 * @Modified By:
 */
public interface CustomLogic {

    Page<CustomVO> getCustoms(String keyword, Pageable pageable);

    List<CustomVO> getCustomsForReceipt();

    List<CustomVO> getCustomsForSale();

    List<CustomVO> getCustomsForPurchase();

    CustomVO getCustom(Long customId);

    Long createCustom(CustomVO customVO);

    void updateCustom(CustomVO customVO);

    void deleteCustom(Long customId);
}
