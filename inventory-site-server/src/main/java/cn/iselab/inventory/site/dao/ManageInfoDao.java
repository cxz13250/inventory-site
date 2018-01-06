package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.ManageInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:50 2018/1/6
 * @Modified By:
 */
@Transactional
public interface ManageInfoDao extends CrudRepository<ManageInfo,Long>{

    ManageInfo findById(long id);

    @Query("select m from ManageInfo m")
    List<ManageInfo> findAllInfos();
}
