package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.web.data.SaleDetailVO;


/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:37 2017/12/8
 * @Modified By:
 */
public interface SaleDetailLogic {

    SaleDetailVO getSaleDetail(Long startTime,Long endTime);

    Long createSaleDetail(SaleDetailVO vo);

    void deleteSaleDetail(long id);
}
