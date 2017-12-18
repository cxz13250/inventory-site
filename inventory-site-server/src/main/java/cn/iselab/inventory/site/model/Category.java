package cn.iselab.inventory.site.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ROGK on 2017/9/15.
 * 分类
 */
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "super_id")
    private long superId;

    @Column(name = "description")
    private String description;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "is_delete")
    private Boolean deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSuperId() {
        return superId;
    }

    public void setSuperId(long superId) {
        this.superId = superId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Boolean isDelete() {
        return deleted;
    }

    public void setDelete(Boolean deleted) {
        this.deleted = deleted;
    }
}
