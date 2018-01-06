package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.ManageInfo;
import cn.iselab.inventory.site.service.ManageService;
import cn.iselab.inventory.site.web.logic.ManageLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:49 2018/1/6
 * @Modified By:
 */
@Service
public class ManageLogicImpl implements ManageLogic {

    @Autowired
    ManageService manageService;

    @Override
    public ManageInfo getManageInfo(long startTime, long endTime){
        List<ManageInfo> manageInfos=manageService.getManageInfos();
        Random random=new Random();
        long id=0;
        while (id==0){
            id= random.nextInt(manageInfos.size()+1);
        }
        return manageService.getManageInfo(id);
    }
}
