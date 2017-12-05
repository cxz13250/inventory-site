package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:04 2017/12/5
 * @Modified By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleOrderVO {

    private String number;

    private Long customId;

    private String operator;

    private String repository;

    private Double total;

    private Double discount;

    private String extra;

    private Boolean type;

    private Long status;
}
