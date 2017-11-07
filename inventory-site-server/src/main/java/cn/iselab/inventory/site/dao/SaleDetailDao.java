package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.SaleDetail;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ROGK on 2017/11/1.
 */
@Transactional
public interface SaleDetailDao extends CrudRepository<SaleDetail,Long> {

    List<SaleDetail> findByGoodName(String goodName);
}
