package cn.iselab.inventory.site.web.data;

import lombok.Data;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:11 2017/12/19
 * @Modified By:
 */
@Data
public class GoodsItemVO {

    private Long goodId;

    private String goodName;

    private Long number;

    private Double price;

    private Double sum;

    private String extra;
}
