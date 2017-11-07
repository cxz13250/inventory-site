package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.PurchaseOrder;
import cn.iselab.inventory.site.model.SaleDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:48 2017/11/6
 * @Modified By:
 */
public interface SaleDetailService {

    SaleDetail createSaleDetail(SaleDetail saleDetail);

    List<SaleDetail> getSaleDetails(String goodName);

    SaleDetail getSaleDetail(long saleDetailId);

    void updateSaleDetail(SaleDetail saleDetail);

    void deleteSaleDetail(SaleDetail saleDetail);
}
