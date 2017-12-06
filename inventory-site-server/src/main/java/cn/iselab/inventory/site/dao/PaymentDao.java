package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Payment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by ROGK on 2017/11/1.
 */
@Transactional
public interface PaymentDao extends CrudRepository<Payment,Long> ,JpaSpecificationExecutor<Payment>{

    @Query("select p from Payment p where p.number=:number and p.delete=false ")
    Payment findByNumber(String number);
}
