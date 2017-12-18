package cn.iselab.inventory.site.web.data;

import lombok.Data;

import java.util.List;

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

    private Long roleId;

    private String roleName;

    private List<MenuVO> menus;
}
