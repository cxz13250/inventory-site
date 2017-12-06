package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:02 2017/12/7
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferVO {

    private Long accountId;

    private Double money;

    private String extra;
}
