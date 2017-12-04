package cn.iselab.inventory.site.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ROGK on 2017/10/30.
 * 银行账户
 */
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private double balance;

    @Column(name = "bank")
    private String bank;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "is_delete")
    private boolean delete;

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
