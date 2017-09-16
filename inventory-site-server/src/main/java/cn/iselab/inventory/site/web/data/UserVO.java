package cn.iselab.inventory.site.web.data;

import lombok.Data;

/**
 * Created by ROGK on 2017/9/14.
 */
@Data
public class UserVO extends BaseVO{

    private Long id;

    private String email;

    private String name;

    private String mobile;

    private String password;

    private Long createTime;
}
