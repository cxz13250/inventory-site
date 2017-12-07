package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.service.ComplimentaryService;
import cn.iselab.inventory.site.web.logic.ComplimentaryLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:59 2017/12/8
 * @Modified By:
 */
@Service
public class ComplimentaryLogicImpl implements ComplimentaryLogic {

    @Autowired
    ComplimentaryService complimentaryService;
}
