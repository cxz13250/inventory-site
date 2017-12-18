package cn.iselab.inventory.site.model;

import javax.persistence.*;

/**
 * Created by ROGK on 2017/9/15.
 * 商品
 */
@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "category")
    private long category;

    @Column(name = "inventory")
    private long inventory;

    @Column(name = "cost_price")
    private double costPrice;

    @Column(name = "retail_price")
    private double retailPrice;

    @Column(name = "current_cost_price")
    private double currentCostPrice;

    @Column(name = "current_retail_price")
    private double currentRetailPrice;

    @Column(name = "is_delete")
    private boolean deleted;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getInventory() {
        return inventory;
    }

    public void setInventory(long inventory) {
        this.inventory = inventory;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getCurrentCostPrice() {
        return currentCostPrice;
    }

    public void setCurrentCostPrice(double currentCostPrice) {
        this.currentCostPrice = currentCostPrice;
    }

    public double getCurrentRetailPrice() {
        return currentRetailPrice;
    }

    public void setCurrentRetailPrice(double currentRetailPrice) {
        this.currentRetailPrice = currentRetailPrice;
    }

    public boolean isDelete() {
        return deleted;
    }

    public void setDelete(boolean deleted) {
        this.deleted = deleted;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }
}
