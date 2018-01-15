package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Account;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ROGK on 2017/11/1.
 */
@Transactional
public interface AccountDao extends CrudRepository<Account,Long>,PagingAndSortingRepository<Account,Long>,JpaSpecificationExecutor<Account>{

    @Query("select a from Account a where a.deleted = 0")
    List<Account> getAll();

    List<Account> findByName(String name);
}
