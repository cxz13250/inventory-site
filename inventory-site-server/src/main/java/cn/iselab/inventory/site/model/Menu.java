package cn.iselab.inventory.site.model;

import javax.persistence.*;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:11 2017/12/4
 * @Modified By:
 */
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "menu")
    private String menu;

    @Column(name = "detail")
    private String detail;

    @Column(name = "url")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
