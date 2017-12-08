package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午3:18 2017/12/8
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleOrderItemVO {

    private String goodName;

    private String goodModel;

    private Long sum;

    private Double price;

    private Double total;

    private String extra;
}
