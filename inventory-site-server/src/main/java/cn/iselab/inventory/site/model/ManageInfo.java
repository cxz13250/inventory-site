package cn.iselab.inventory.site.model;

import javax.persistence.*;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:43 2018/1/6
 * @Modified By:
 */
@Entity
@Table(name = "manage_info")
public class ManageInfo {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "sale_income")
    private double saleIncome;

    @Column(name = "sale_discount")
    private double saleDiscount;

    @Column(name = "goods_income")
    private double goodsIncome;

    @Column(name = "goods_discount")
    private double goodsDiscount;

    @Column(name = "sale_cost")
    private double saleCost;

    @Column(name = "goods_cost")
    private double goodsCost;

    @Column(name = "profit")
    private double profit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSaleIncome() {
        return saleIncome;
    }

    public void setSaleIncome(double saleIncome) {
        this.saleIncome = saleIncome;
    }

    public double getSaleDiscount() {
        return saleDiscount;
    }

    public void setSaleDiscount(double saleDiscount) {
        this.saleDiscount = saleDiscount;
    }

    public double getGoodsIncome() {
        return goodsIncome;
    }

    public void setGoodsIncome(double goodsIncome) {
        this.goodsIncome = goodsIncome;
    }

    public double getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(double goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public double getSaleCost() {
        return saleCost;
    }

    public void setSaleCost(double saleCost) {
        this.saleCost = saleCost;
    }

    public double getGoodsCost() {
        return goodsCost;
    }

    public void setGoodsCost(double goodsCost) {
        this.goodsCost = goodsCost;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
