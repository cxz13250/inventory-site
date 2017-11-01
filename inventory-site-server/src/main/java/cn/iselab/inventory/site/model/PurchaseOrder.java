package cn.iselab.inventory.site.model;

import javax.persistence.*;

/**
 * Created by ROGK on 2017/11/1.
 */
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "number")
    private String number;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "repository")
    private String repository;

    @Column(name = "operator")
    private String operator;

    @Column(name = "extra")
    private String extra;

    @Column(name = "total")
    private double total;

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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
