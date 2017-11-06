package cn.iselab.inventory.site.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ROGK on 2017/11/1.
 * 库存报警单/报损单/报溢单 由type字段区分 取值见StockOrderConstants
 */
@Entity
@Table(name = "stock_order")
public class StockOrder {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "good_id")
    private long goodId;

    @Column(name = "number")
    private long number;

    @Column(name = "type")
    private long type;

    @Column(name = "status")
    private long status;

    @Column(name = "create_time")
    private Timestamp createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
