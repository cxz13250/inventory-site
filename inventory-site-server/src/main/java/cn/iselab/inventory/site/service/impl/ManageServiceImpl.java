package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.ManageInfoDao;
import cn.iselab.inventory.site.model.ManageInfo;
import cn.iselab.inventory.site.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:49 2018/1/6
 * @Modified By:
 */
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    ManageInfoDao manageInfoDao;

    @Override
    public ManageInfo getManageInfo(long id){
        return manageInfoDao.findById(id);
    }

    @Override
    public List<ManageInfo> getManageInfos(){
        return manageInfoDao.findAllInfos();
    }
}
