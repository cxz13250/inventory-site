package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:10 2017/12/5
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {

    private Long id;

    private String name;

    private Double balance;

    private String bank;

    private String bankNum;

    private Long createTime;
}
