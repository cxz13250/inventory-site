package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.SaleOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Created by ROGK on 2017/11/1.
 */
@Transactional
public interface SaleOrderDao extends CrudRepository<SaleOrder,Long> ,JpaSpecificationExecutor<SaleOrder>{

    @Query("select s from SaleOrder s where s.number = :number")
    SaleOrder findByNumber(@Param("number") String number);
}
