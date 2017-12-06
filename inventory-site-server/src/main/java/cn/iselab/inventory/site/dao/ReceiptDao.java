package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Receipt;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by ROGK on 2017/11/1.
 */
@Transactional
public interface ReceiptDao extends CrudRepository<Receipt,Long> ,JpaSpecificationExecutor<Receipt>{

    @Query("select r from Receipt r where r.number=:number and r.delete=false ")
    Receipt findByNumber(String number);
}
