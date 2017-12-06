package cn.iselab.inventory.site.web.data;

import cn.iselab.inventory.site.model.PaymentEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:44 2017/12/6
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVO {

    private String number;

    private Long operator;

    private Long account;

    private Double total;

    private Long status;

    private Long createTime;

    private List<PaymentEntry> entries;
}
