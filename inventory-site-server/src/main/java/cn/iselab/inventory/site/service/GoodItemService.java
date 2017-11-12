package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.GoodItem;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:51 2017/11/6
 * @Modified By:
 */
public interface GoodItemService {

    GoodItem createGoodItem(GoodItem goodItem);

    List<GoodItem> getGoodItems(long orderId);

    GoodItem getGoodItem(long id);

    void updateGoodItem(GoodItem goodItem);

    void deleteGoodItem(GoodItem goodItem);
}
