package cn.iselab.inventory.site.model;

import javax.persistence.*;

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
    private String cumstomId;

    @Column(name = "operator")
    private long operator;

    @Column(name = "transfer_list")
    private String transferList;

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

    public String getCumstomId() {
        return cumstomId;
    }

    public void setCumstomId(String cumstomId) {
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
}
