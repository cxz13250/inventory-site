package cn.iselab.inventory.site.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:08 2017/12/4
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomVO {

    private Long id;

    private Long type;

    private int level;

    private String name;

    private String mobile;

    private String address;

    private String postCode;

    private String email;

    private Double receiveLimit;

    private Double receive;

    private Double pay;

    private Long salesman;
}
