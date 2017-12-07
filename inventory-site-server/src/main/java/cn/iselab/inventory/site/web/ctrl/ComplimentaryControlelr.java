package cn.iselab.inventory.site.web.ctrl;

import cn.iselab.inventory.site.web.logic.ComplimentaryLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:59 2017/12/8
 * @Modified By:
 */

@RestController
public class ComplimentaryControlelr extends BaseController {

    @Autowired
    ComplimentaryLogic complimentaryLogic;
}
