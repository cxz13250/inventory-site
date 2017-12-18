package cn.iselab.inventory.site.web.data;

import lombok.Data;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:49 2017/12/17
 * @Modified By:
 */
@Data
public class UserOperationVO {

    private String name;

    private String Operation;

    private Long createTime;

    private String ip;
}
