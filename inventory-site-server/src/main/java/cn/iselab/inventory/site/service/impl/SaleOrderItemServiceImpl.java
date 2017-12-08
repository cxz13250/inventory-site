package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.SaleOrderItemDao;
import cn.iselab.inventory.site.model.SaleOrderItem;
import cn.iselab.inventory.site.service.SaleOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午3:21 2017/12/8
 * @Modified By:
 */
@Service
public class SaleOrderItemServiceImpl implements SaleOrderItemService{

    @Autowired
    SaleOrderItemDao saleOrderItemDao;

    @Override
    public SaleOrderItem createSaleItem(SaleOrderItem item){
        item.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return saleOrderItemDao.save(item);
    }

    @Override
    public List<SaleOrderItem> getSaleItems(long orderId){
        return saleOrderItemDao.findByOrderId(orderId);
    }

}
