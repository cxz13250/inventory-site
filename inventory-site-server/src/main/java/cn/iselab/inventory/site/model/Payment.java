package cn.iselab.inventory.site.model;

import javax.persistence.*;

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
    private long operator;

    @Column(name = "account_id")
    private long account;

    @Column(name = "total")
    private double total;

    @Column(name = "status")
    private long status;

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

    public long getOperator() {
        return operator;
    }

    public void setOperator(long operator) {
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
}
