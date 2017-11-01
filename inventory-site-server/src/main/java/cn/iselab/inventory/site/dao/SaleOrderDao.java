package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.SaleOrder;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by ROGK on 2017/11/1.
 */
@Transactional
public interface SaleOrderDao extends CrudRepository<SaleOrder,Long> {
}
