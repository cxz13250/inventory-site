package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.SaleDetail;
import cn.iselab.inventory.site.web.data.SaleDetailVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:40 2017/12/8
 * @Modified By:
 */
@Service
public class SaleDetailVOWrapper extends BaseWrapper<SaleDetailVO,SaleDetail>{

    @Override
    public SaleDetailVO wrap(SaleDetail saleDetail){
        SaleDetailVO vo = new SaleDetailVO();
        return vo;
    }

    @Override
    public SaleDetail unwrap(SaleDetailVO vo){
        SaleDetail saleDetail=new SaleDetail();
        return saleDetail;
    }
}
