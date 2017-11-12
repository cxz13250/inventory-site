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

    @Column(name = "create_time")
    private Timestamp createTime;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
