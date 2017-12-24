package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:45 2017/12/7
 * @Modified By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockOrderVO {

    private Long id;

    private Long goodId;

    private String goodName;

    private Long number;

    private Long type;

    private Long status;

    private Long createTime;
}
