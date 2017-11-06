package cn.iselab.inventory.site.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by ROGK on 2017/11/1.
 * 付款单条目
 */
@Entity
@Table(name = "payment_entry")
public class PaymentEntry {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "payment_id")
    private long payment;

    @Column(name = "name")
    private String name;

    @Column(name = "money")
    private double money;

    @Column(name = "extra")
    private String extra;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPayment() {
        return payment;
    }

    public void setPayment(long payment) {
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
