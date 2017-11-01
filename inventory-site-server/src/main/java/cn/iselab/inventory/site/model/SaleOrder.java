package cn.iselab.inventory.site.model;

import javax.persistence.*;

/**
 * Created by ROGK on 2017/11/1.
 */
@Entity
@Table(name = "sale_order")
public class SaleOrder {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "number")
    private String number;

    @Column(name = "custom_id")
    private long customId;

    @Column(name = "operator")
    private long operator;

    @Column(name = "repository")
    private String repository;

    @Column(name = "total")
    private double total;

    @Column(name = "discount")
    private double discount;

    @Column(name = "extra")
    private String extra;

    @Column(name = "type")
    private boolean type;

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

    public long getCustomId() {
        return customId;
    }

    public void setCustomId(long customId) {
        this.customId = customId;
    }

    public long getOperator() {
        return operator;
    }

    public void setOperator(long operator) {
        this.operator = operator;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
