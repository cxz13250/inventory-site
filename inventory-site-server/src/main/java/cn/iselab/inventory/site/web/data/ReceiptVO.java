package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:50 2017/12/6
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptVO {

    private String number;

    private Long cumstomId;

    private Long operator;

    private Double total;

    private Long status;

    private Long createTime;
}
