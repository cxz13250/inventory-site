package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.SaleDetailDao;
import cn.iselab.inventory.site.model.SaleDetail;
import cn.iselab.inventory.site.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:48 2017/11/6
 * @Modified By:
 */
@Service
public class SaleDetailServiceImpl implements SaleDetailService {

    @Autowired
    SaleDetailDao saleDetailDao;

    @Override
    public SaleDetail createSaleDetail(SaleDetail saleDetail){
        return saleDetailDao.save(saleDetail);
    }

    @Override
    public List<SaleDetail> getSaleDetails(String goodName){
        return saleDetailDao.findByGoodName(goodName);
    }

    @Override
    public SaleDetail getSaleDetail(long saleDetailId){
        return saleDetailDao.findOne(saleDetailId);
    }

    @Override
    public void updateSaleDetail(SaleDetail saleDetail){
        saleDetailDao.save(saleDetail);
    }

    @Override
    public void deleteSaleDetail(SaleDetail saleDetail){
        saleDetailDao.delete(saleDetail);
    }
}
