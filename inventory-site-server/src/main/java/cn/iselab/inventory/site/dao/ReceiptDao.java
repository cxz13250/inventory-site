package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Receipt;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ROGK on 2017/11/1.
 */
@Transactional
public interface ReceiptDao extends CrudRepository<Receipt,Long> ,JpaSpecificationExecutor<Receipt>, PagingAndSortingRepository<Receipt,Long>{

    @Query("SELECT r " +
            "FROM Receipt r " +
            "WHERE r.number = :number")
    List<Receipt> findByNumber(@Param("number") String number);

//    List<Receipt> findByNumber(String number);
}
