package cn.iselab.inventory.site.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ROGK on 2017/11/1.
 * 付款单
 */
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "number")
    private String number;

    @Column(name = "operator")
    private String operator;

    @Column(name = "account")
    private long account;

    @Column(name = "custom")
    private long custom;

    @Column(name = "total")
    private double total;

    @Column(name = "status")
    private long status;

    @Column(name = "is_delete")
    private boolean deleted=false;

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
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

    public long getCustom() {
        return custom;
    }

    public void setCustom(long custom) {
        this.custom = custom;
    }
}
