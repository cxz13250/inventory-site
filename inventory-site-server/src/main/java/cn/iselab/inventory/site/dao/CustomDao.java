package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Custom;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by ROGK on 2017/9/15.
 */
@Transactional
public interface CustomDao extends CrudRepository<Custom, Long>{
}
