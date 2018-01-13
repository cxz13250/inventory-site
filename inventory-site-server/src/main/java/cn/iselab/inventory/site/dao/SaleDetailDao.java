package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.SaleDetail;
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
public interface SaleDetailDao extends CrudRepository<SaleDetail,Long>,JpaSpecificationExecutor<SaleDetail>,PagingAndSortingRepository<SaleDetail,Long>{

    @Query("select s from SaleDetail s")
    List<SaleDetail> findAll();
}
