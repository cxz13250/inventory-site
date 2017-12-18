package cn.iselab.inventory.site.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ROGK on 2017/10/30.
 */
@Entity
@Table(name = "sale_detail")
public class SaleDetail {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "sale_time")
    private Date saleTime;

    @Column(name = "good_name")
    private String goodName;

    @Column(name = "model")
    private String model;

    @Column(name = "number")
    private long number;

    @Column(name = "price")
    private double price;

    @Column(name = "total")
    private double total;

    @Column(name = "is_delete")
    private boolean deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isDelete() {
        return deleted;
    }

    public void setDelete(boolean deleted) {
        this.deleted = deleted;
    }
}
