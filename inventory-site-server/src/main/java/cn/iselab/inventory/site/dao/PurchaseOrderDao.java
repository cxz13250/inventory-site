package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Created by ROGK on 2017/11/1.
 */
@Transactional
public interface PurchaseOrderDao extends CrudRepository<PurchaseOrder,Long> ,JpaSpecificationExecutor<PurchaseOrder>{

    @Query("select p from PurchaseOrder p where p.number=:number")
    PurchaseOrder findByNumber(@Param("number")String number);
}
