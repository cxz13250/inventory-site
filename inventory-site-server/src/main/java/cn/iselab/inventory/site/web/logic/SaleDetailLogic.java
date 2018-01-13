package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.SaleDetail;
import cn.iselab.inventory.site.web.data.SaleDetailVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:37 2017/12/8
 * @Modified By:
 */
public interface SaleDetailLogic {

    SaleDetailVO getSaleDetail(Long startTime,Long endTime);

    Page<SaleDetailVO> getSaleDetails(Pageable pageable,Long startTime,Long endTime,String goodName)throws Exception;

    List<SaleDetailVO> getSaleDetailsForExcel(long startTime,long endTime);

    Long createSaleDetail(SaleDetailVO vo);

    void deleteSaleDetail(long id);
}
