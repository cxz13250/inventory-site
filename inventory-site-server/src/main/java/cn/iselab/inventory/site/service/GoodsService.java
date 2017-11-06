package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:37 2017/11/6
 * @Modified By:
 */
public interface GoodsService {

    void createGood(Goods goods);

    Goods getGoodById(long goodId);

    Page<Goods> getGoods(String keyword, Pageable pageable);

    void updateGood(Goods goods);

    void deleteGood(Goods goods);
}
