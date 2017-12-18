package cn.iselab.inventory.site.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ROGK on 2017/11/1.
 * 收款单
 */
@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "number")
    private String number;

    @Column(name = "custom_id")
    private long cumstomId;

    @Column(name = "operator")
    private long operator;

    @Column(name = "transfer_list")
    private String transferList;

    @Column(name = "total")
    private double total;

    @Column(name = "status")
    private long status;

    @Column(name = "is_delete")
    private boolean deleted;

    @Column(name = "create_time")
    private Timestamp createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getCumstomId() {
        return cumstomId;
    }

    public void setCumstomId(long cumstomId) {
        this.cumstomId = cumstomId;
    }

    public long getOperator() {
        return operator;
    }

    public void setOperator(long operator) {
        this.operator = operator;
    }

    public String getTransferList() {
        return transferList;
    }

    public void setTransferList(String transferList) {
        this.transferList = transferList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public boolean isDelete() {
        return deleted;
    }

    public void setDelete(boolean deleted) {
        this.deleted = deleted;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
