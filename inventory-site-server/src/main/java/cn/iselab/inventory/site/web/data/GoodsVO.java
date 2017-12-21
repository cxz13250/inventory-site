package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:57 2017/12/4
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVO {

    private Long id;

    private String name;

    private String model;

    private Long categoryId;

    private String categoryName;

    private Long inventory;

    private Double costPrice;

    private Double retailPrice;

    private Double currentCostPrice;

    private Double currentRetailPrice;

}
