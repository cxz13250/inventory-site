package cn.iselab.inventory.site.web.data;

import lombok.Data;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:23 2017/12/25
 * @Modified By:
 */
@Data
public class StockCheckVO {

    private String name;

    private String model;

    private Long inventory;

    private Double price;

    private Long paymentId;

    private String number;
}
