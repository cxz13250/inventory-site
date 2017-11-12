package cn.iselab.inventory.site.model;

import javax.persistence.*;

/**
 * Created by ROGK on 2017/11/1.
 * 进货单中的入库商品
 */
@Entity
@Table(name = "good_item")
public class GoodItem {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "good_id")
    private long goodId;

    @Column(name = "good_name")
    private String goodName;

    @Column(name = "number")
    private long number;

    @Column(name = "price")
    private double price;

    @Column(name = "sum")
    private double sum;

    @Column(name = "extra")
    private String extra;

    @Column(name = "order_id")
    private long orderId;

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

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
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

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
