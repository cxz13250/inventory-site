package cn.iselab.inventory.site.web.logic;


import cn.iselab.inventory.site.web.data.GoodsVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:48 2017/12/4
 * @Modified By:
 */
public interface GoodsLogic {

    Page<GoodsVO> getGoodsList(String keyword,Pageable pageable);

    GoodsVO getGoods(Long goodsId);

    Long createGoods(GoodsVO goodsVO);

    void updateGoods(GoodsVO goodsVO);

    void deleteGoods(Long goodsId);
}
