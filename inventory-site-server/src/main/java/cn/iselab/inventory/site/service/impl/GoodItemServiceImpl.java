package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.GoodItemDao;
import cn.iselab.inventory.site.model.GoodItem;
import cn.iselab.inventory.site.service.GoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:51 2017/11/6
 * @Modified By:
 */
@Service
public class GoodItemServiceImpl implements GoodItemService {

    @Autowired
    private GoodItemDao goodItemDao;

    @Override
    public GoodItem createGoodItem(GoodItem goodItem){
        return goodItemDao.save(goodItem);
    }

    @Override
    public List<GoodItem> getGoodItems(long orderId){
        return goodItemDao.findByOrderId(orderId);
    }

    @Override
    public GoodItem getGoodItem(long id){
        return goodItemDao.findOne(id);
    }

    @Override
    public void updateGoodItem(GoodItem goodItem){
        goodItemDao.save(goodItem);
    }

    @Override
    public void deleteGoodItem(GoodItem goodItem){
        goodItemDao.delete(goodItem);
    }
}
