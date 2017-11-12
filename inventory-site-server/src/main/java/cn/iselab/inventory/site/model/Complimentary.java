package cn.iselab.inventory.site.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ROGK on 2017/11/1.
 * 赠送单
 */
@Entity
@Table(name = "complimentary")
public class Complimentary {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "custom_id")
    private long customId;

    @Column(name = "content")
    private String content;

    @Column(name = "total")
    private double total;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public long getCustomId() {
        return customId;
    }

    public void setCustomId(long customId) {
        this.customId = customId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
