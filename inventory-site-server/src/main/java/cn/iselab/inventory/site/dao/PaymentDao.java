package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Payment;
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
public interface PaymentDao extends CrudRepository<Payment,Long> ,JpaSpecificationExecutor<Payment>, PagingAndSortingRepository<Payment, Long>{

    @Query("select p from Payment p where p.number = :number")
    List<Payment> findByNumber(@Param("number") String number);
}
