package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.web.data.GoodsVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:37 2017/11/6
 * @Modified By:
 */
public interface GoodsService {

    Goods createGood(Goods goods);

    Goods getGoodById(long goodId);

    Goods getGoodByName(String name);

    Page<Goods> getGoods(String keyword, Pageable pageable);

    List<Goods> getGoodsForPurchase();

    List<Goods> getGoodByCategory(long categoryId);

    void updateGood(Goods goods);

    void deleteGood(Goods goods);

    void updateInfo(Goods goods, GoodsVO vo);

    void deleteGoods(Goods goods);
}
