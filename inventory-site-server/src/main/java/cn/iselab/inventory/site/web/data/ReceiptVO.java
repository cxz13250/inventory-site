package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private Long customId;

    private String customName;

    private String operator;

    private Double total;

    private Long status;

    private Long createTime;

    private List<TransferVO> transfers;
}
