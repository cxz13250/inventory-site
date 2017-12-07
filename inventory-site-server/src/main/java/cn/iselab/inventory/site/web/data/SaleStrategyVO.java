package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:27 2017/12/8
 * @Modified By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleStrategyVO {

    private Long id;

    private String name;

    private String content;

    private Long startTime;

    private Long endTime;
}
