package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午5:59 2017/12/5
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderVO {

    private String number;

    private String supplier;

    private String repository;

    private String operator;

    private String extra;

    private Double total;

    private Boolean type;

    private Long status;

    private Long createTime;

}
