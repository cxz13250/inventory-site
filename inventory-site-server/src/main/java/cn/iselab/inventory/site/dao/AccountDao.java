package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Account;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by ROGK on 2017/11/1.
 */
@Transactional
public interface AccountDao extends CrudRepository<Account,Long>{
}
